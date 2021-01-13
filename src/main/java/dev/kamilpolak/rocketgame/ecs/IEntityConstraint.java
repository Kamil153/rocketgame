package dev.kamilpolak.rocketgame.ecs;

public interface IEntityConstraint {
    boolean check(Entity entity);
}
