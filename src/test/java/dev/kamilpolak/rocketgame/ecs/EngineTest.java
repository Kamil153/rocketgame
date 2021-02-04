package dev.kamilpolak.rocketgame.ecs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class EngineTest {
    static class TestComponent1 implements IComponent {}
    static class TestComponent2 implements IComponent {}

    @Test
    public void queryEntitiesTest() {
        Entity entity1 = new Entity();
        entity1.addComponent(new TestComponent1());

        Entity entity2 = new Entity();
        entity2.addComponent(new TestComponent1());
        entity2.addComponent(new TestComponent2());

        Entity entity3 = new Entity();

        Engine engine = new Engine();
        engine.addEntity(entity1);
        engine.addEntity(entity2);
        engine.addEntity(entity3);

        Query query1 = new Query().include(TestComponent1.class).exclude(TestComponent2.class);
        Set<Entity> result1 = engine.queryEntities(query1);
        Assert.assertTrue(result1.contains(entity1));
        Assert.assertEquals(1, result1.size());
    }
}
