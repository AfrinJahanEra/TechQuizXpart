package src;


public enum Subject {
    // Computer Science
    OOP("Object Oriented Programming"),
    DSA("Data Structures and Algorithms"),
    AI("Artificial Intelligence"),
    DATABASE_MANAGEMENT_SYSTEMS("Database Management Systems"),
    OPERATING_SYSTEMS("Operating Systems"),
    COMPUTER_NETWORKS("Computer Networks"),
    DIGITAL_ELECTRONICS("Digital Electronics"),
    
    // Electrical Engineering
    ELECTRICAL_CIRCUIT("Electrical Circuit"),
    POWER_SYSTEM("Power System"),
    ELECTRICAL_MACHINES("Electrical Machines"),
    POWER_GENERATION("Power Generation"),
    POWER_TRANSMISSION("Power Transmission"),
    ELECTRONIC_DEVICES("Electronic Devices"),
    POWER_ELECTRONICS("Power Electronics"),
    CONTROL_SYSTEMS("Control Systems"),
    ELECTRICAL_MEASUREMENTS("Electrical Measurements"),
    RENEWABLE_ENERGY("Renewable Energy"),
    TRANSFORMERS("Transformers"),
    
    // Civil Engineering
    SOIL_MECHANICS("Soil Mechanics"),
    SOLID_MECHANICS("Solid Mechanics"),
    GEOLOGY("Geology"),
    FOUNDATION_ENGINEERING("Foundation Engineering"),
    CONCRETE_TECHNOLOGY("Concrete Technology"),
    STRUCTURAL_ENGINEERING("Structural Engineering"),
    BUILDING_MATERIALS("Building Materials"),
    SURVEYING("Surveying"),
    ENVIRONMENTAL_ENGINEERING("Environmental Engineering"),
    TRANSPORTATION_ENGINEERING("Transportation Engineering"),
    WATER_SUPPLY_ENGINEERING("Water Supply Engineering"),
    HYDRAULICS("Hydraulics"),
    FLUID_MECHANICS("Fluid Mechanics"),
    STRUCTURAL_DYNAMICS("Structural Dynamics"),
    
    // Mechanical Engineering
    THERMODYNAMICS("Thermodynamics"),
    HEAT_TRANSFER("Heat Transfer"),
    STRENGTH_OF_MATERIALS("Strength of Materials"),
    MANUFACTURING("Manufacturing"),
    MACHINE_DESIGN("Machine Design"),
    THEORY_OF_MACHINES("Theory of Machines"),
    AUTOMOBILE_ENGINEERING("Automobile Engineering");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Subject fromString(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Subject text cannot be null");
        }
        String trimmed = text.trim();
        for (Subject subject : Subject.values()) {
            if (subject.displayName.equalsIgnoreCase(trimmed)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("No Subject enum constant for: " + text);
    }
}