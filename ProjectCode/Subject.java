package ProjectCode;

public enum Subject {
    OOP("Object Oriented Programming"),
    DSA("Data Structures and Algorithms"),
    AI("Artificial Intelligence"),
    ELECTRICAL_CIRCUIT("Electrical Circuit"),
    POWER_SYSTEM("Power System"),
    DLD("Digital Logic Design"),
    SOIL_MECHANICS("Soil Mechanics"),
    SOLID_MECHANICS("Solid Mechanics"),
    GEOLOGY("Geology"),
    FLUID_MECHANICS("Fluid Mechanics"),
    STRUCTURAL_DYNAMICS("Structural Dynamics"),
    THERMODYNAMICS("Thermodynamics");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    public static Subject fromString(String text) {
        for (Subject subject : Subject.values()) {
            if (subject.displayName.equalsIgnoreCase(text)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }
}
