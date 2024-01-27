package org.studentresource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentResourceManager<T extends StudentResource> {
      private final List<T> resources = new ArrayList<>();



    public void addResource(T resource) {
        if (resource == null) {
            throw new NullPointerException("Resource cannot be null");
        }

        resources.removeIf(r -> r.getId().equals(resource.getId()));
        resources.add(resource);
    }

    public void removeResource(T resource) {
        if (resource != null) {
            resources.remove(resource);
        }

    }

    public T getResource(String resourceId) {
        if (resourceId == null || resourceId.isEmpty()) {
            return null;
        }

        Optional<T> matchingResource = resources.stream()
                .filter(resource -> resourceId.equals(resource.getId()))
                .findFirst();

        return matchingResource.orElse(null);
    }

}
