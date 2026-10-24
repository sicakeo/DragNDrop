package com.chien.flightcontrol.model;

public enum NodeType {
        SOURCE("Source", 0, 1),
        DESTINATION("Destination", 1, 0),
        SUMMER("Summer", 2, 1),
        PID("PID", 1, 1),
        GAIN("Gain", 1, 1),
        FILTER("Filter", 1, 1),
        DEAD_BAND("Dead Band", 1, 1),
        SWITCH("Switch", 2, 1),
        KINEMAT("Kinemat", 1, 1),
        FCSFUNCTION("FCSFunction", 1, 1);

        public final String label;
        public final int inPorts;
        public final int outPorts;

        NodeType(String label, int inPorts, int outPorts) {
            this.label = label;
            this.inPorts = inPorts;
            this.outPorts = outPorts;
        }

        public String getLabel() {
            return label;
        }

        @Override public String toString() { return label; }

        public static NodeType fromLabel(String s) {
            for (NodeType t : values()) {
                if (t.label.equalsIgnoreCase(s)) return t;
            }
            return GAIN;
        }
    }
