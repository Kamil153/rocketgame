package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class AndConstraint implements IEntityConstraint {
    Set<Class<? extends Component>> include;

    public AndConstraint(Collection<Class<? extends Component>> include) {
        this.include = new HashSet<>(include);
    }

    public boolean check(Entity entity) {
        for(Class<? extends Component> component: include) {
            if(!entity.hasComponent(component)) {
                return false;
            }
        }
        return true;
    }
}
