package dev.kamilpolak.rocketgame.ecs;

import org.junit.Test;
import org.junit.Assert;

public class EntityTest {
    @Test
    public void getComponentTest() {
        class TestComponent implements Component { }
        class TestComponent2 implements Component { }
        TestComponent testComponent = new TestComponent();
        Entity testEntity = new Entity();
        testEntity.addComponent(testComponent);
        Assert.assertEquals(testComponent, testEntity.getComponent(TestComponent.class));
        Assert.assertNull(testEntity.getComponent(TestComponent2.class));
    }
}
