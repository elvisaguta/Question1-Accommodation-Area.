
/**
 * AccommodationArea - Base class for all accommodation areas
 * Provides common functionality for managing occupants and lighting
 * 
 * @author Speke Apartments Management System
 * @version 1.0
 */
public abstract class AccommodationArea {
    // Private instance variables (encapsulation)
    private String areaName;
    private int occupantCount;
    private boolean[] lights; // Array to track light states (3 lights)
    private int maxCapacity;
    
    // Constructor
    public AccommodationArea(String areaName, int maxCapacity) {
        this.areaName = areaName;
        this.maxCapacity = maxCapacity;
        this.occupantCount = 0;
        this.lights = new boolean[3]; // Initialize 3 lights (all OFF)
    }
    
    // Public method to add occupants
    public boolean addOccupants(int count) {
        if (count < 0) {
            System.out.println("ERROR: Cannot add negative occupants!");
            return false;
        }
        
        if (occupantCount + count > maxCapacity) {
            System.out.println("ERROR: Exceeds maximum capacity of " + maxCapacity);
            return false;
        }
        
        occupantCount += count;
        System.out.println("Successfully added " + count + " occupant(s).");
        return true;
    }
    
    // Public method to remove occupants
    public boolean removeOccupants(int count) {
        if (count < 0) {
            System.out.println("ERROR: Cannot remove negative occupants!");
            return false;
        }
        
        if (occupantCount - count < 0) {
            System.out.println("ERROR: Cannot remove more occupants than present!");
            System.out.println("Current occupants: " + occupantCount);
            return false;
        }
        
        occupantCount -= count;
        System.out.println("Successfully removed " + count + " occupant(s).");
        return true;
    }
    
    // Public method to switch ON a light
    public boolean switchLightOn(int lightNumber) {
        if (lightNumber < 1 || lightNumber > 3) {
            System.out.println("ERROR: Light number must be 1, 2, or 3!");
            return false;
        }
        
        int index = lightNumber - 1; // Convert to array index
        
        if (lights[index]) {
            System.out.println("Light " + lightNumber + " is already ON.");
        } else {
            lights[index] = true;
            System.out.println("Light " + lightNumber + " switched ON.");
        }
        return true;
    }
    
    // Public method to switch OFF a light
    public boolean switchLightOff(int lightNumber) {
        if (lightNumber < 1 || lightNumber > 3) {
            System.out.println("ERROR: Light number must be 1, 2, or 3!");
            return false;
        }
        
        int index = lightNumber - 1; // Convert to array index
        
        if (!lights[index]) {
            System.out.println("Light " + lightNumber + " is already OFF.");
        } else {
            lights[index] = false;
            System.out.println("Light " + lightNumber + " switched OFF.");
        }
        return true;
    }
    
    // Public method to display status report
    public void displayStatus() {
        System.out.println("\n========================================");
        System.out.println("AREA STATUS REPORT");
        System.out.println("========================================");
        System.out.println("Area Name: " + areaName);
        System.out.println("Current Occupants: " + occupantCount + "/" + maxCapacity);
        System.out.println("----------------------------------------");
        System.out.println("Lighting Status:");
        for (int i = 0; i < lights.length; i++) {
            String status = lights[i] ? "ON" : "OFF";
            System.out.println("  Light " + (i + 1) + ": " + status);
        }
        System.out.println("========================================\n");
    }
    
    // Getters
    public String getAreaName() {
        return areaName;
    }
    
    public int getOccupantCount() {
        return occupantCount;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    protected boolean[] getLights() {
        return lights;
    }
    
    // Abstract method to be implemented by subclasses
    public abstract void displayAreaInfo();
}
2. GymArea.java (Subclass)
/**
 * GymArea - Represents the Gym accommodation area
 * Extends AccommodationArea with gym-specific features
 * 
 * @author Speke Apartments Management System
 * @version 1.0
 */
public class GymArea extends AccommodationArea {
    private String[] equipment;
    private boolean airConditioningOn;
    private int temperature; // in Celsius
    
    // Constructor
    public GymArea() {
        super("Gym Area", 50); // Max capacity of 50 people
        this.equipment = new String[]{"Treadmills", "Weight Machines", "Yoga Mats", "Dumbbells"};
        this.airConditioningOn = false;
        this.temperature = 25; // Default temperature
    }
    
    @Override
    public void displayAreaInfo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          GYM AREA INFORMATION          ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Capacity: " + getMaxCapacity() + " persons");
        System.out.println("Available Equipment:");
        for (String item : equipment) {
            System.out.println("  • " + item);
        }
        System.out.println("Air Conditioning: " + (airConditioningOn ? "ON" : "OFF"));
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("════════════════════════════════════════\n");
    }
    
    // Gym-specific methods
    public void toggleAirConditioning() {
        airConditioningOn = !airConditioningOn;
        System.out.println("Air Conditioning turned " + (airConditioningOn ? "ON" : "OFF"));
    }
    
    public void setTemperature(int temp) {
        if (temp < 16 || temp > 30) {
            System.out.println("ERROR: Temperature must be between 16°C and 30°C");
            return;
        }
        this.temperature = temp;
        System.out.println("Temperature set to " + temp + "°C");
    }
}
3. SwimmingArea.java (Subclass)
/**
 * SwimmingArea - Represents the Swimming Pool accommodation area
 * Extends AccommodationArea with swimming-specific features
 * 
 * @author Speke Apartments Management System
 * @version 1.0
 */
public class SwimmingArea extends AccommodationArea {
    private double waterTemperature; // in Celsius
    private String poolType;
    private boolean lifeguardPresent;
    private int waterDepth; // in meters
    
    // Constructor
    public SwimmingArea() {
        super("Swimming Pool Area", 30); // Max capacity of 30 people
        this.waterTemperature = 28.0;
        this.poolType = "Olympic Size";
        this.lifeguardPresent = true;
        this.waterDepth = 2; // 2 meters deep
    }
    
    @Override
    public void displayAreaInfo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      SWIMMING POOL AREA INFORMATION    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Capacity: " + getMaxCapacity() + " persons");
        System.out.println("Pool Type: " + poolType);
        System.out.println("Water Temperature: " + waterTemperature + "°C");
        System.out.println("Water Depth: " + waterDepth + " meters");
        System.out.println("Lifeguard: " + (lifeguardPresent ? "Present" : "Not Present"));
        System.out.println("════════════════════════════════════════\n");
    }
    
    // Swimming-specific methods
    public void adjustWaterTemperature(double temp) {
        if (temp < 20 || temp > 35) {
            System.out.println("ERROR: Water temperature must be between 20°C and 35°C");
            return;
        }
        this.waterTemperature = temp;
        System.out.println("Water temperature adjusted to " + temp + "°C");
    }
    
    public void toggleLifeguard() {
        lifeguardPresent = !lifeguardPresent;
        System.out.println("Lifeguard status: " + (lifeguardPresent ? "Present" : "Not Present"));
    }
}
4. AccommodationManager.java (Main Application)
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * AccommodationManager - Main application for Speke Apartments
 * Manages accommodation areas with interactive menu system
 * 
 * @author Speke Apartments Management System
 * @version 1.0
 */
public class AccommodationManager {
    
    private static GymArea gymArea;
    private static SwimmingArea swimmingArea;
    private static AccommodationArea activeArea;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Initialize areas
        gymArea = new GymArea();
        swimmingArea = new SwimmingArea();
        activeArea = gymArea; // Default active area
        scanner = new Scanner(System.in);
        
        // Display welcome message
        displayWelcomeMessage();
        
