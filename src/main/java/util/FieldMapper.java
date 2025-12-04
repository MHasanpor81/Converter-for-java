package util;

import java.util.HashMap;
import java.util.Map;

public class FieldMapper<S, T> {

    private final Class<S> sourceClass;
    private final Class<T> targetClass;


    private final Map<String, String> sourceToTarget = new HashMap<>();
    private final Map<String, String> targetToSource = new HashMap<>();

    public FieldMapper(Class<S> sourceClass, Class<T> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public FieldMapper<S, T> map(String sourceField, String targetField) {
        sourceToTarget.put(sourceField, targetField);
        targetToSource.put(targetField, sourceField);
        return this;
    }

    public Map<String, String> getSourceToTargetMapping() { return sourceToTarget; }
    public Map<String, String> getTargetToSourceMapping() { return targetToSource; }

    public Class<S> getSourceClass() { return sourceClass; }
    public Class<T> getTargetClass() { return targetClass; }
}