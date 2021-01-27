package dev.kamilpolak.rocketgame.ecs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class EngineTest {
    static class TestComponent1 implements Component {}
    static class TestComponent2 implements Component {}

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

    @Test
    public void addedEntitiesTest() {
        Engine engine = new Engine();
        Entity entity1 = new Entity();
        engine.addEntity(entity1);
        Assert.assertTrue(engine.getAddedEntities().contains(entity1));
        engine.update(1);
        Assert.assertTrue(engine.getAddedEntities().isEmpty());
    }

    @Test
    public void removedEntitiesTest() {
        Engine engine = new Engine();
        Entity entity1 = new Entity();
        engine.addEntity(entity1);
        engine.removeEntity(entity1);
        Assert.assertTrue(engine.getRemovedEntities().contains(entity1));
        Assert.assertTrue(engine.getAddedEntities().isEmpty());
        engine.update(1);
        Assert.assertTrue(engine.getRemovedEntities().isEmpty());
    }

    @Test
    public void mutatedEntitiesTest() {
        Engine engine = new Engine();
        Entity entity1 = new Entity();
        engine.addEntity(entity1);
        entity1.addComponent(new TestComponent1());
        Assert.assertTrue(engine.getMutatedEntities().contains(entity1));
        engine.update(1);
        Assert.assertTrue(engine.getMutatedEntities().isEmpty());
        entity1.removeComponent(TestComponent1.class);
        Assert.assertTrue(engine.getMutatedEntities().contains(entity1));
        engine.removeEntity(entity1);
        Assert.assertTrue(engine.getMutatedEntities().isEmpty());
    }
}
