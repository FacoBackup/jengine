package com.pine.tools.types;

public enum RenderingMode {
    ALBEDO(0),
    NORMAL(1),
    TANGENT(2),
    DEPTH(3),
    AO(4),
    DETAIL(5),
    LIGHT_ONLY(6),
    METALLIC(7),
    ROUGHNESS(8),
    G_AO(9),
    AMBIENT(10),
    POSITION(11),
    UV(12),
    RANDOM(13),
    OVERDRAW(14),
    LIGHT_COMPLEXITY(15),
    LIGHT_QUANTITY(16);

    private final int id;

    RenderingMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
