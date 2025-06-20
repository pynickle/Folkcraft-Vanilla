package com.euphony.folkcraft_vanilla.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.DyeColor;
import com.mojang.math.Axis;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HudAnimalsRenderer {
    private static final List<VirtualAnimal> virtualAnimals = new ArrayList<>();
    private static final Random random = new Random();
    private static final int AREA_WIDTH = 100;
    private static final int AREA_HEIGHT = 60;
    private static final int AREA_X = 10; // 左下角X坐标
    private static final int AREA_Y_OFFSET = 70; // 距离底部的偏移量
    private static final int MAX_ANIMALS = 3;
    private static boolean initialized = false;

    // 动物行为状态，移除RUNNING状态
    private enum AnimalState {
        IDLE, // 站立不动
        WALKING, // 缓慢行走
    }

    public static void init() {
        // 注册HUD渲染事件
        ClientGuiEvent.RENDER_HUD.register(HudAnimalsRenderer::renderAnimalsOnHud);
        
        // 注册客户端tick事件更新动物位置
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            // 只有当世界加载后才初始化动物
            if (!initialized && minecraft.level != null) {
                addInitialAnimals();
                initialized = true;
            }

            if (initialized) {
                updateAnimals();
            }
        });
    }

    private static void addInitialAnimals() {
        // 清空现有动物
        virtualAnimals.clear();
        
        // 添加一些动物，位置分散，避免初始重叠
        for (int i = 0; i < MAX_ANIMALS; i++) {
            addRandomAnimal(i);
        }
    }
    
    private static void addRandomAnimal(int index) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) {
            return; // 安全检查，避免在世界未加载时创建实体
        }

        // 目前只添加羊，后续可以扩展更多动物类型
        Sheep sheep = EntityType.SHEEP.create(minecraft.level);
        if (sheep != null) {
            // 随机羊的颜色
            sheep.setColor(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
            
            // 将区域分成几块，避免动物初始位置重叠
            float sectionWidth = AREA_WIDTH / Math.max(MAX_ANIMALS, 1);
            float x = index * sectionWidth + random.nextFloat() * sectionWidth * 0.6f + sectionWidth * 0.2f;
            float y = random.nextFloat() * AREA_HEIGHT;

            VirtualAnimal virtualAnimal = new VirtualAnimal(
                sheep,
                x,
                y,
                0f, 0f
            );
            virtualAnimals.add(virtualAnimal);
        }
    }
    
    // 检查两个动物之间是否碰撞
    private static boolean checkCollision(VirtualAnimal a1, VirtualAnimal a2, float minDistance) {
        float dx = a2.x - a1.x;
        float dy = a2.y - a1.y;
        float distanceSquared = dx * dx + dy * dy;
        return distanceSquared < minDistance * minDistance;
    }

    // 处理动物之间的碰撞，避免重叠
    private static void resolveCollisions() {
        // 羊之间的最小距离
        float minDistance = 10.0f;

        for (int i = 0; i < virtualAnimals.size(); i++) {
            VirtualAnimal animal1 = virtualAnimals.get(i);

            for (int j = i + 1; j < virtualAnimals.size(); j++) {
                VirtualAnimal animal2 = virtualAnimals.get(j);

                // 检查两个动物是否太近
                if (checkCollision(animal1, animal2, minDistance)) {
                    // 计算两个动物之间的向量
                    float dx = animal2.x - animal1.x;
                    float dy = animal2.y - animal1.y;
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);

                    // 如果距离为0，给一个小的随机位移避免除零错误
                    if (distance < 0.1f) {
                        dx = (random.nextFloat() - 0.5f) * 0.2f;
                        dy = (random.nextFloat() - 0.5f) * 0.2f;
                        distance = 0.1f;
                    }

                    // 归一化向量
                    float nx = dx / distance;
                    float ny = dy / distance;

                    // 计算需要位移的距离
                    float overlap = minDistance - distance;

                    // 两个动物各自往相反方向移动一半的重叠距离
                    float moveX = nx * overlap * 0.5f;
                    float moveY = ny * overlap * 0.5f;

                    // 根据状态决定推力强度
                    float pushFactor1 = animal1.state == AnimalState.IDLE ? 1.0f : 0.5f;
                    float pushFactor2 = animal2.state == AnimalState.IDLE ? 1.0f : 0.5f;

                    // 更新位置
                    animal2.x += moveX * pushFactor2;
                    animal2.y += moveY * pushFactor2;
                    animal1.x -= moveX * pushFactor1;
                    animal1.y -= moveY * pushFactor1;

                    // 确保不会超出边界
                    animal1.x = Mth.clamp(animal1.x, 0, AREA_WIDTH);
                    animal1.y = Mth.clamp(animal1.y, 0, AREA_HEIGHT);
                    animal2.x = Mth.clamp(animal2.x, 0, AREA_WIDTH);
                    animal2.y = Mth.clamp(animal2.y, 0, AREA_HEIGHT);

                    // 如果两只羊试图走到同一个地方，让它们各自选择���的目标
                    if (animal1.hasTargetPosition && animal2.hasTargetPosition) {
                        float targetDx = animal2.targetX - animal1.targetX;
                        float targetDy = animal2.targetY - animal1.targetY;
                        float targetDistSq = targetDx * targetDx + targetDy * targetDy;

                        // 如果目标也靠得很近，一个羊重新选择目标
                        if (targetDistSq < minDistance * minDistance * 2) {
                            if (random.nextBoolean()) {
                                animal1.hasTargetPosition = false;
                            } else {
                                animal2.hasTargetPosition = false;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void updateAnimals() {
        for (VirtualAnimal animal : virtualAnimals) {
            // 更新行为状态
            animal.updateState();

            // 保存上一个位置和角度
            float prevX = animal.x;
            float prevY = animal.y;

            // 根据当前状态更新速度和位置
            switch (animal.state) {
                case IDLE:
                    // 逐渐减速至停止
                    animal.speedX = Mth.lerp(0.1f, animal.speedX, 0);
                    animal.speedY = Mth.lerp(0.1f, animal.speedY, 0);
                    break;

                case WALKING:
                    if (!animal.hasTargetPosition) {
                        // 随机选择一个新的目标点
                        float maxDistance = 30.0f;
                        float angle = random.nextFloat() * (float) Math.PI * 2;
                        float distance = random.nextFloat() * maxDistance;

                        float targetDx = (float) Math.cos(angle) * distance;
                        float targetDy = (float) Math.sin(angle) * distance;

                        animal.targetX = Mth.clamp(animal.x + targetDx, 5, AREA_WIDTH - 5);
                        animal.targetY = Mth.clamp(animal.y + targetDy, 5, AREA_HEIGHT - 5);
                        animal.hasTargetPosition = true;
                    }

                    // 计算到目标点的方向矢量
                    float dx = animal.targetX - animal.x;
                    float dy = animal.targetY - animal.y;
                    float dist = (float) Math.sqrt(dx * dx + dy * dy);

                    // 如果已经到达目标点附近，取消目标
                    if (dist < 1.0f) {
                        animal.hasTargetPosition = false;
                    } else {
                        // 正常化方向矢量并设置适当的速度
                        float walkSpeedFactor = 0.2f;

                        // 直接使用目标方向计算速度，确保方向一致性
                        animal.speedX = Mth.lerp(0.05f, animal.speedX, (dx / dist) * walkSpeedFactor);
                        animal.speedY = Mth.lerp(0.05f, animal.speedY, (dy / dist) * walkSpeedFactor);
                    }
                    break;
            }

            // 更新位置
            animal.x += animal.speedX;
            animal.y += animal.speedY;

            // 边界检查
            if (animal.x < 0) {
                animal.x = 0;
                animal.speedX *= -0.3f;
                animal.hasTargetPosition = false;
            } else if (animal.x > AREA_WIDTH) {
                animal.x = AREA_WIDTH;
                animal.speedX *= -0.3f;
                animal.hasTargetPosition = false;
            }
            
            if (animal.y < 0) {
                animal.y = 0;
                animal.speedY *= -0.3f;
                animal.hasTargetPosition = false;
            } else if (animal.y > AREA_HEIGHT) {
                animal.y = AREA_HEIGHT;
                animal.speedY *= -0.3f;
                animal.hasTargetPosition = false;
            }
            
            // 关键修改：根据实际移动方向设置朝向，而不是目标点方向
            // 只有当速度足够大时才更新方向，防止静止状态下的随机抖动
            float speedMagnitude = (float) Math.sqrt(animal.speedX * animal.speedX + animal.speedY * animal.speedY);
            if (speedMagnitude > 0.01f) {
                // 直接基于当前速度方向设置目标朝向
                animal.targetYRot = calculateYaw(animal.speedX, animal.speedY);
                // 更快速地调整朝向以匹配移动方向
                animal.currentYRot = lerpAngle(animal.currentYRot, animal.targetYRot, 0.2f);
            }
            animal.entity.setYRot(animal.currentYRot);

            // 计算移动距离，用于动画
            float moveDistance = (float) Math.sqrt((animal.x - prevX) * (animal.x - prevX) +
                                                  (animal.y - prevY) * (animal.y - prevY));

            // 更平滑地更新动画状态
            float targetAnimSpeed = Mth.clamp(speedMagnitude * 3.0f, 0.0f, 0.4f);
            animal.animSpeed = Mth.lerp(0.1f, animal.animSpeed, targetAnimSpeed);

            // 更新行走动画
            animal.walkAnimation.update(animal.animSpeed, 0.1f);

            // 实体应该自己处理动画，但在我们的虚拟环境中需要手动更新
            animal.entity.tickCount++;
        }

        // 处理动物之间的碰撞，避免重叠
        resolveCollisions();
    }

    /**
     * 平滑地插值两个角度，处理角度环绕（比如从359度到1度）
     */
    private static float lerpAngle(float a, float b, float t) {
        float diff = ((b - a + 540) % 360) - 180;
        return a + diff * t;
    }

    private static float calculateYaw(float speedX, float speedY) {
        // 根据速度方向计算实体的朝向角度（用弧度表示）
        if (Math.abs(speedX) < 0.001f && Math.abs(speedY) < 0.001f) {
            return 0;
        }
        return (float) (Math.atan2(speedX, -speedY) * (180.0 / Math.PI));
    }

    private static void renderAnimalsOnHud(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null || virtualAnimals.isEmpty()) {
            return;
        }

        // 获取屏幕高度
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        // 渲染区域的Y坐标（从底部向上偏移）
        int areaY = screenHeight - AREA_Y_OFFSET;

        MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
        EntityRenderDispatcher entityRenderer = minecraft.getEntityRenderDispatcher();

        for (VirtualAnimal animal : virtualAnimals) {
            // 保存当前矩阵状态
            guiGraphics.pose().pushPose();

            // 将渲染位置移动到HUD左下角的设定区域
            guiGraphics.pose().translate(AREA_X + animal.x, areaY + animal.y, 100);

            // 缩放实体大小，降低为原来的60%左右
            float scale = 9.0f; // 原来是15.0f
            guiGraphics.pose().scale(-scale, -scale, scale);

            // 设置30度俯视角度 (X轴旋转)
            guiGraphics.pose().mulPose(Axis.XP.rotationDegrees(30.0F));

            // 旋转实体以便正确显示
            guiGraphics.pose().mulPose(Axis.YP.rotationDegrees(180.0F - animal.entity.getYRot()));

            // 从DeltaTracker获取部分tick时间
            float tickDelta = deltaTracker.getGameTimeDeltaPartialTick(false);

            // 渲染实体
            RenderSystem.runAsFancy(() -> {
                entityRenderer.render(animal.entity, 0, 0, 0, 0, tickDelta, guiGraphics.pose(), bufferSource, 15728880);
            });

            // 恢复矩阵状态
            guiGraphics.pose().popPose();
        }

        // 确保所有渲染都被提交
        bufferSource.endBatch();
    }
    
    // 虚拟动物类，用于在HUD上管理动物
    private static class VirtualAnimal {
        final Animal entity;
        float x, y;
        float speedX, speedY;
        float targetX, targetY;  // 目标位置
        boolean hasTargetPosition = false;  // 是否有目标位置
        AnimalState state = AnimalState.IDLE;  // 当前行为状态
        int stateTimer = 0;  // 当前状态持续时间
        float currentYRot = 0;  // 当前朝向
        float targetYRot = 0;   // 目标朝向
        float animSpeed = 0;    // 动画速度
        final net.minecraft.world.entity.WalkAnimationState walkAnimation;

        VirtualAnimal(Animal entity, float x, float y, float speedX, float speedY) {
            this.entity = entity;
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
            this.currentYRot = 0;
            this.targetYRot = 0;
            this.walkAnimation = entity.walkAnimation;

            // 设置初始朝向
            this.entity.setYRot(0);

            // 初始化行走动画 - 设置动物静止
            this.walkAnimation.update(0f, 1.0f);

            // 随机初始状态和持续时间
            updateState();
        }

        void updateState() {
            // 减少状态计时器
            stateTimer--;

            // 如果计时器到0，随机选择新状态
            if (stateTimer <= 0) {
                float chance = random.nextFloat();
                if (chance < 0.65f) {
                    state = AnimalState.IDLE;
                    stateTimer = 30 + random.nextInt(120); // 站立1-4秒
                } else {
                    state = AnimalState.WALKING;
                    stateTimer = 80 + random.nextInt(160); // 行走2.5-7.5秒
                    hasTargetPosition = false; // 强制选择新目标
                }
            }
        }
    }
}
