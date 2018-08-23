package com.app.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



// DODAC BUIILDERY! ------------------------------------------------------------------------------------------------------------------------------------


public class GuaranteeComponent {
    private List<String> components = new ArrayList<>();

    private GuaranteeComponent(GuaranteeComponentBuilder builder) {
        this.components = builder.components;
    }

    public static GuaranteeComponentBuilder builder() {
        return new GuaranteeComponentBuilder();
    }

    public String component() {
        StringBuilder sb = new StringBuilder(components.get(0) + "( ");
        sb.append(components.subList(1, components.size()).stream().collect(Collectors.joining((", "))));
        sb.append(" )");
        return sb.toString().toLowerCase();
    }

    public static class GuaranteeComponentBuilder {
        private List<String> components = new ArrayList<>();

        public GuaranteeComponentBuilder addTable(String tableName) {
            if (!components.isEmpty()) {
                return this;
            }
            components.add(0, MessageFormat.format("create tabel if not exists {0}", tableName));
            return this;
        }

        public GuaranteeComponent build() {
            return new GuaranteeComponent(this);
        }
    }
}

