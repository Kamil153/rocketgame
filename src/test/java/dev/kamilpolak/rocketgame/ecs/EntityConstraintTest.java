package dev.kamilpolak.rocketgame.ecs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class EntityConstraintTest {
    static class TestComponent1 implements Component {}
    static class TestComponent2 implements Component {}

    @Test
    public void checkTest() {
        Entity entity1 = new Entity();
        entity1.addComponent(new TestComponent1());
        entity1.addComponent(new TestComponent2());
        Entity entity2 = new Entity();
        entity2.addComponent(new TestComponent1());
        IEntityConstraint constraint1 = new AndConstraint(Arrays.asList(TestComponent1.class, TestComponent2.class));
        IEntityConstraint constraint2 = new NotConstraint(Collections.singletonList(TestComponent2.class));
        IEntityConstraint constraint3 = new AndConstraint(Collections.emptyList());
        Assert.assertTrue(constraint1.check(entity1));
        Assert.assertFalse(constraint2.check(entity1));
        Assert.assertTrue(constraint2.check(entity2));
        Assert.assertFalse(constraint1.check(entity2));
        Assert.assertTrue(constraint3.check(entity1));
    }
}
