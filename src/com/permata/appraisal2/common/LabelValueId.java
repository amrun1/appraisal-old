package com.permata.appraisal2.common;

public class LabelValueId<I, V, L> {

    private I id;
    private L label;
    private V value;

    public LabelValueId(I id, V value, L label) {
        super();
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public L getLabel() {
        return label;
    }

    public void setLabel(L label) {
        this.label = label;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}