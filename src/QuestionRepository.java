package src;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// public class Quiz {
//     private User user;
//     private List<Question> questions;
//     private int currentQuestionIndex;
//     private double score;
//     private static List<Question> allQuestions = new ArrayList<>();

//     static {
//         // Predefined questions for CSE subjects
//         allQuestions.add(new Question(
//                 "What is the main principle of OOP that allows objects to share behavior and attributes based on their class?",
//                 new String[] { "Inheritance", "Encapsulation", "Polymorphism",
//                         "Abstraction" },
//                 "Abstraction",
//                 Subject.OOP));
//         allQuestions.add(new Question(
//                 "Which OOP concept allows restricting access to certain components within a class?",
//                 new String[] { "Inheritance", "Polymorphism", "Encapsulation",
//                         "Abstraction" },
//                         "Encapsulation",
//                 Subject.OOP));
//         allQuestions.add(new Question(
//                 "Which of the following best describes polymorphism in OOP?",
//                 new String[] { "Ability to inherit multiple classes",
//                         "Ability to create objects from classes",
//                         "Ability to override methods in subclasses",
//                         "Ability to hide implementation details" },
//                         "Ability to override methods in subclasses",
//                 Subject.OOP));
//         allQuestions.add(new Question(
//                 "In OOP, which feature allows a class to have more than one method with the same name but different parameters?",
//                 new String[] { "Inheritance", "Encapsulation", "Polymorphism",
//                         "Abstraction" },
//                         "Polymorphism",
//                 Subject.OOP));
//         allQuestions.add(new Question(
//                 "Which OOP concept emphasizes defining a blueprint for creating objects with common properties and behaviors?",
//                 new String[] { "Inheritance", "Encapsulation", "Polymorphism",
//                         "Abstraction" },
//                         "Abstraction",
//                 Subject.OOP));
//         allQuestions.add(new Question(
//                 "Which of the following data structures uses a Last-In-First-Out (LIFO) approach?",
//                 new String[] { "Queue", "Stack", "Linked List", "Binary Tree" },
//                 "Stack",
//                 Subject.DSA));
//         allQuestions.add(new Question(
//                 "What is the time complexity of searching for an element in a sorted array using Binary Search?",
//                 new String[] { "O(n)", "O(log n)", "O(n log n)", "O(1)" },
//                 "O(log n)",
//                 Subject.DSA));
//         allQuestions.add(new Question(
//                 "Which sorting algorithm has the best average-case time complexity?",
//                 new String[] { "Bubble Sort", "Merge Sort", "Selection Sort",
//                         "Insertion Sort" },
//                 "Merge Sort",
//                 Subject.DSA));
//         allQuestions.add(new Question(
//                 "In a singly linked list, which operation has a time complexity of O(1)?",
//                 new String[] { "Insertion at the beginning", "Deletion at the end",
//                         "Searching for an element",
//                         "Traversing the entire list" },
//                 "Insertion at the beginning",
//                 Subject.DSA));
//         allQuestions.add(new Question(
//                 "Which data structure is best suited for implementing a LRU (Least Recently Used) cache?",
//                 new String[] { "Queue", "Stack", "Hash Table", "Linked List" },
//                 "Linked List",
//                 Subject.DSA));
//         allQuestions.add(new Question(
//                 "Which branch of AI focuses on enabling machines to learn from data and improve over time without being explicitly programmed?",
//                 new String[] { "Natural Language Processing (NLP)", "Expert Systems",
//                         "Machine Learning", "Robotics" },
//                 "Machine Learning",
//                 Subject.AI));
//         allQuestions.add(new Question(
//                 "What is the goal of supervised learning in machine learning?",
//                 new String[] { "To learn from unlabeled data",
//                         "To make predictions based on labeled data",
//                         "To optimize a reward function",
//                         "To mimic human-like reasoning" },
//                 "To make predictions based on labeled data",
//                 Subject.AI));
//         allQuestions.add(new Question(
//                 "Which AI technique is used for decision-making based on probabilistic reasoning?",
//                 new String[] { "Genetic Algorithms", "Expert Systems",
//                         "Reinforcement Learning", "Bayesian Networks" },
//                 "Bayesian Networks",
//                 Subject.AI));
//         allQuestions.add(new Question(
//                 "What is the primary function of a neural network in AI?",
//                 new String[] { "Pattern recognition and classification",
//                         "Logical reasoning and deduction",
//                         "Optimal decision-making under uncertainty",
//                         "Speech synthesis and recognition" },
//                 "Pattern recognition and classification",
//                 Subject.AI));
//         allQuestions.add(new Question(
//                 "Which AI approach is focused on mimicking human-like intelligence, reasoning, and problem-solving capabilities?",
//                 new String[] { "Machine Learning", "Expert Systems",
//                         "Natural Language Processing (NLP)",
//                         "Artificial General Intelligence (AGI)" },
//                 "Artificial General Intelligence (AGI)",
//                 Subject.AI));

//         // Predefined questions for EEE subjects
//         allQuestions.add(new Question(
//                 "What is the unit of electrical resistance?",
//                 new String[] { "Ampere (A)", "Volt (V)", "Ohm (Ω)", "Watt (W)" },
//                 "Ampere (A)",
//                 Subject.ELECTRICAL_CIRCUIT));
//         allQuestions.add(new Question(
//                 "What is the Kirchhoff's Voltage Law (KVL) used for in electrical circuits?",
//                 new String[] { "To calculate the total current in a circuit",
//                         "To calculate the total resistance in a circuit",
//                         "To calculate the total power dissipation in a circuit",
//                         "To analyze voltage drops in a closed loop of a circuit" },
//                 "To calculate the total power dissipation in a circuit",
//                 Subject.ELECTRICAL_CIRCUIT));
//         allQuestions.add(new Question(
//                 "In a parallel electrical circuit, what happens to the total resistance as more resistors are added?",
//                 new String[] { "It decreases", "It increases", "It remains constant",
//                         " It depends on the type of resistors" },
//                 "It remains constant",
//                 Subject.ELECTRICAL_CIRCUIT));
//         allQuestions.add(new Question(
//                 "What is the power formula (P) for an electrical circuit with voltage (V) and current (I)?",
//                 new String[] { "P = V - I", " P = V / I", "P = V * I", " P = I / V" },
//                 "P = V * I",
//                 Subject.ELECTRICAL_CIRCUIT));
//         allQuestions.add(new Question(
//                 "Which of the following is an example of an active component in an electrical circuit?",
//                 new String[] { "Resistor", "Capacitor", "Inductor", "Transistor" },
//                 "Transistor",
//                 Subject.ELECTRICAL_CIRCUIT));
//         allQuestions.add(new Question(
//                 "What is the standard frequency of alternating current (AC) power in most regions of the world?",
//                 new String[] { "50 Hz", "60 Hz", "50 kHz", "60 kHz" },
//                 "50 Hz",
//                 Subject.POWER_SYSTEM));
//         allQuestions.add(new Question(
//                 "What is the purpose of a transformer in an electrical power system?",
//                 new String[] { "To convert AC to DC power",
//                         "To step up or step down voltage levels",
//                         "To regulate power factor",
//                         " To store electrical energy" },
//                 "To step up or step down voltage levels",
//                 Subject.POWER_SYSTEM));
//         allQuestions.add(new Question(
//                 "What is the unit of electrical power?",
//                 new String[] { " Ampere (A)", "Volt (V)", "Watt (W)", "Ohm (Ω)" },
//                 "Watt (W)",
//                 Subject.POWER_SYSTEM));
//         allQuestions.add(new Question(
//                 "What is the function of a circuit breaker in a power system?",
//                 new String[] { "To regulate voltage",
//                         "To control current flow in one direction",
//                         "To protect the system from overloads and short circuits",
//                         "To convert AC to DC power" },
//                 "To protect the system from overloads and short circuits",
//                 Subject.POWER_SYSTEM));
//         allQuestions.add(new Question(
//                 "What is the purpose of a transmission line in a power grid?",
//                 new String[] { "To generate electrical energy",
//                         "To distribute electricity to consumers",
//                         "To step up voltage for long-distance transmission",
//                         "To store excess electrical energy" },
//                 "To distribute electricity to consumers",
//                 Subject.POWER_SYSTEM));
//         allQuestions.add(new Question(
//                 "What is the basic building block of digital circuits?",
//                 new String[] { "Capacitor", "Transistor", " Resistor", "Diode" },
//                 "Transistor",
//                 Subject.DLD));
//         allQuestions.add(new Question(
//                 "Which logic gate produces a high output only when all its inputs are high?",
//                 new String[] { "AND gate", "OR gate", "NOT gate", "XOR gate" },
//                 "AND gate",
//                 Subject.DLD));
//         allQuestions.add(new Question(
//                 "What is the result of applying the Boolean operation AND to two inputs, both being 1 (True)?",
//                 new String[] { "0 (False)", "1 (True)", "Undefined",
//                         "Cannot be determined" },
//                         "1 (True)",
//                 Subject.DLD));
//         allQuestions.add(new Question(
//                 "What is the function of a flip-flop in digital circuits?",
//                 new String[] { " To perform arithmetic operations",
//                         "To store binary data temporarily",
//                         "To convert analog signals to digital signals",
//                         "To generate clock signals" },
//                 "To store binary data temporarily",
//                 Subject.DLD));
//         allQuestions.add(new Question(
//                 "Which digital component is used to synchronize different parts of a digital circuit?",
//                 new String[] { "Multiplexer", "Demultiplexer", "Flip-flop",
//                         " Counter" },
//                 "Flip-flop",
//                 Subject.DLD));

//         // Predefined questions for CE subjects
//         allQuestions.add(new Question(
//                 "Which soil classification system is commonly used to classify soils based on their particle size distribution?",
//                 new String[] { "Unified Soil Classification System (USCS)",
//                         "AASHTO Classification System",
//                         "British Standard Classification System",
//                         "ASTM Classification System" },
//                 "Unified Soil Classification System (USCS)",
//                 Subject.SOIL_MECHANICS));
//         allQuestions.add(new Question(
//                 "What property of soil determines its ability to withstand deformation under load without failure?",
//                 new String[] { "Permeability", "Porosity", "Compressibility",
//                         "Shear strength" },
//                 "Shear strength",
//                 Subject.SOIL_MECHANICS));
//         allQuestions.add(new Question(
//                 "Which soil type has the smallest particle size and the highest water retention capacity?",
//                 new String[] { "Gravel", "Sand", "Silt", "Clay" },
//                 "Clay",
//                 Subject.SOIL_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the term for the ratio of the volume of voids to the total volume of soil?",
//                 new String[] { "Porosity", "Density", "Moisture content",
//                         "Specific gravity" },
//                 "Porosity",
//                 Subject.SOIL_MECHANICS));
//         allQuestions.add(new Question(
//                 "Which soil behavior describes the ability of soil particles to stick together and resist internal movement?",
//                 new String[] { "Cohesion", "Friction", "Plasticity", "Consolidation" },
//                 "Cohesion",
//                 Subject.SOIL_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the primary purpose of calculating stress in solid mechanics?",
//                 new String[] { "To determine material density",
//                         "To evaluate deformation",
//                         "To analyze material strength", "To measure strain" },
//                 "To analyze material strength",
//                 Subject.SOLID_MECHANICS));
//         allQuestions.add(new Question(
//                 "Which of the following materials is known for its high tensile strength?",
//                 new String[] { "Rubber", "Glass", "Steel", "Wood" },
//                 "Steel",
//                 Subject.SOLID_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the relationship between stress (σ), force (F), and cross-sectional area (A) in solid mechanics?",
//                 new String[] { "σ = F / A", "F = σ * A", "A = F / σ", "F = A / σ" },
//                 "σ = F / A",
//                 Subject.SOLID_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the term for the measure of a material's ability to withstand deformation without rupture?",
//                 new String[] { "Elastic modulus", "Yield strength", "Ultimate strength",
//                         "Hardness" },
//                 "Yield strength",
//                 Subject.SOLID_MECHANICS));
//         allQuestions.add(new Question(
//                 "Which solid mechanics concept describes the deformation of a material under applied loads?",
//                 new String[] { "Stress", "Strain", "Modulus of elasticity",
//                         "Poisson's ratio" },
//                 "Strain",
//                 Subject.SOLID_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the hardest known natural mineral on the Mohs scale of mineral hardness?",
//                 new String[] { "Quartz", "Talc", "Diamond", "Feldspar" },
//                 "Diamond",
//                 Subject.GEOLOGY));
//         allQuestions.add(new Question(
//                 "Which type of rock forms from the solidification of molten magma or lava?",
//                 new String[] { "Sedimentary rock", "Metamorphic rock", "Igneous rock",
//                         "Conglomerate rock" },
//                 "Igneous rock",
//                 Subject.GEOLOGY));
//         allQuestions.add(new Question(
//                 "What geological process is responsible for the formation of sedimentary rocks?",
//                 new String[] { "Weathering and erosion", "Heat and pressure",
//                         "Melting and solidification", "Foliation" },
//                 "Weathering and erosion",
//                 Subject.GEOLOGY));
//         allQuestions.add(new Question(
//                 "What is the term for the breaking down and transportation of rock and soil materials by natural processes?",
//                 new String[] { "Erosion", "Deposition", "Metamorphism", "Subduction" },
//                 "Erosion",
//                 Subject.GEOLOGY));
//         allQuestions.add(new Question(
//                 "Which geological era is known as the Age of Reptiles due to the dominance of dinosaurs?",
//                 new String[] { "Paleozoic Era", "Mesozoic Era", "Cenozoic Era",
//                         "Precambrian Era" },
//                 "Mesozoic Era",
//                 Subject.GEOLOGY));

//         // Predefined questions for ME subjects
//         allQuestions.add(new Question(
//                 "What is the fundamental principle used to explain the upward buoyant force experienced by an object submerged in a fluid?",
//                 new String[] { "Archimedes' Principle", "Bernoulli's Principle",
//                         "Pascal's Law", "Newton's Law of Viscosity" },
//                 "Archimedes' Principle",
//                 Subject.FLUID_MECHANICS));
//         allQuestions.add(new Question(
//                 "In fluid mechanics, what term refers to the resistance of a fluid to flow?",
//                 new String[] { "Pressure", "Viscosity", "Density", "Surface Tension" },
//                 "Viscosity",
//                 Subject.FLUID_MECHANICS));
//         allQuestions.add(new Question(
//                 "According to Bernoulli's Equation, an increase in fluid velocity is associated with a(n) ___ in pressure.",
//                 new String[] { "Increase", "Decrease", "Constant",
//                         "None of the above" },
//                 "Decrease",
//                 Subject.FLUID_MECHANICS));
//         allQuestions.add(new Question(
//                 "Which law states that the volume of a given mass of gas is directly proportional to its absolute temperature, assuming constant pressure?",
//                 new String[] { "Boyle's Law", "Charles's Law", "Gay-Lussac's Law",
//                         "Dalton's Law" },
//                 "Charles's Law",
//                 Subject.FLUID_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the term for the measure of a fluid's resistance to shear stress?",
//                 new String[] { "Viscosity", "Surface Tension", "Buoyancy", "Pressure" },
//                 "Viscosity",
//                 Subject.FLUID_MECHANICS));
//         allQuestions.add(new Question(
//                 "What is the primary difference between static analysis and dynamic analysis in structural engineering?",
//                 new String[] { " Static analysis considers time-dependent loads, while dynamic analysis does not.",
//                         "Dynamic analysis considers time-dependent loads and their effects on structures.",
//                         " Static analysis is limited to linear materials, while dynamic analysis considers nonlinear materials.",
//                         "Dynamic analysis is only applicable to small-scale structures." },
//                 "Dynamic analysis considers time-dependent loads and their effects on structures.",
//                 Subject.STRUCTURAL_DYNAMICS));
//         allQuestions.add(new Question(
//                 "Which parameter is crucial in determining the natural frequency of a vibrating structure?",
//                 new String[] { "Damping ratio", "Stiffness", "Load magnitude",
//                         "Material density" },
//                 "Stiffness",
//                 Subject.STRUCTURAL_DYNAMICS));
//         allQuestions.add(new Question(
//                 "What type of analysis is often used to study the response of structures subjected to earthquake loads?",
//                 new String[] { "Static analysis", "Dynamic analysis", "Linear analysis",
//                         "Nonlinear analysis" },
//                 "Dynamic analysis",
//                 Subject.STRUCTURAL_DYNAMICS));
//         allQuestions.add(new Question(
//                 "What is the term for the phenomenon where a structure's vibration frequency matches the excitation frequency, leading to resonance?",
//                 new String[] { "Damping", "Amplification", "Natural frequency",
//                         "Frequency response" },
//                 "Natural frequency",
//                 Subject.STRUCTURAL_DYNAMICS));
//         allQuestions.add(new Question(
//                 "Which factor influences the damping characteristics of a structure?",
//                 new String[] { "Material stiffness", " External loads",
//                         "Energy dissipation mechanisms",
//                         "Structural geometry" },
//                 "Energy dissipation mechanisms",
//                 Subject.STRUCTURAL_DYNAMICS));
//         allQuestions.add(new Question(
//                 "Which law of thermodynamics states that energy cannot be created or destroyed, only transferred or converted from one form to another?",
//                 new String[] { "Zeroth Law of Thermodynamics",
//                         "First Law of Thermodynamics",
//                         "Second Law of Thermodynamics",
//                         "Third Law of Thermodynamics" },
//                 "First Law of Thermodynamics",
//                 Subject.THERMODYNAMICS));
//         allQuestions.add(new Question(
//                 "What is the SI unit of heat energy?",
//                 new String[] { "Joule (J)", "Watt (W)", "Newton (N)", "Kelvin (K)" },
//                 "Joule (J)",
//                 Subject.THERMODYNAMICS));
//         allQuestions.add(new Question(
//                 "Which process involves the transfer of heat without a change in temperature?",
//                 new String[] { "Isobaric process", "Adiabatic process",
//                         "Isothermal process", " Isochoric process" },
//                 "Adiabatic process",
//                 Subject.THERMODYNAMICS));
//         allQuestions.add(new Question(
//                 "What does the Carnot efficiency of a heat engine depend on?",
//                 new String[] { "Temperature of the heat source and sink",
//                         "Volume of the working fluid",
//                         "Pressure of the working fluid",
//                         "Specific heat capacity of the working fluid" },
//                 "Temperature of the heat source and sink",
//                 Subject.THERMODYNAMICS));
//         allQuestions.add(new Question(
//                 "According to the second law of thermodynamics, what is the direction of natural heat flow?",
//                 new String[] { "From colder objects to hotter objects",
//                         "From hotter objects to colder objects",
//                         "Equal heat flow in all directions",
//                         "Heat flow is random and unpredictable" },
//                 "From hotter objects to colder objects",
//                 Subject.THERMODYNAMICS));
//     }

//     public Quiz(User user) {
//         this.user = user;
//         this.questions = allQuestions.stream()
//                 .filter(q -> q.getSubject() == user.getSubject())
//                 .collect(Collectors.toList());//filter required subject question among all questions
//         this.currentQuestionIndex = 0;
//         this.score = 0;
//     }

//     public static void addQuestion(Question question) {
//         allQuestions.add(question);
//     }

//     public boolean hasMoreQuestions() {
//         return currentQuestionIndex < questions.size();
//     }

//     public Question getNextQuestion() {
//         return questions.get(currentQuestionIndex++);
//     }

//     public void submitAnswer(String answer) {
//         Question currentQuestion = questions.get(currentQuestionIndex - 1);
//         if (answer == null || answer.trim().isEmpty()) {
//             // Answer is not provided, score remains the same
//             return;
//         }
//         if (currentQuestion.isCorrect(answer)) {
//             score += 1;
//         } else {
//             score -= 0.5;
//         }
//     }
    
//     public double getScore() {
//         return score;
//     }
// }


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class QuestionRepository {
    private static final String QUESTIONS_DIR = "questions";
    private Map<String, List<Question>> departmentQuestions;

    public QuestionRepository() {
        departmentQuestions = new HashMap<>();
        initializeQuestionFiles();
        loadAllQuestions();
    }

    private void initializeQuestionFiles() {
        File dir = new File(QUESTIONS_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String[] departments = {"CSE", "EEE", "CE", "ME"};
        for (String dept : departments) {
            File deptFile = new File(QUESTIONS_DIR + "/" + dept + "_questions.txt");
            if (!deptFile.exists()) {
                try {
                    deptFile.createNewFile();
                    initializeDefaultQuestions(dept);
                } catch (IOException e) {
                    System.err.println("Error creating question file for " + dept + ": " + e.getMessage());
                }
            }
        }
    }

    private void initializeDefaultQuestions(String department) {
        List<Question> defaultQuestions = new ArrayList<>();
        
        switch (department) {
            case "CSE":
                defaultQuestions.add(new Question(
                    "What is the main principle of OOP that allows objects to share behavior and attributes based on their class?",
                    new String[]{"Inheritance", "Encapsulation", "Polymorphism", "Abstraction"},
                    "Abstraction",
                    Subject.OOP
                ));
                defaultQuestions.add(new Question(
                    "Which data structure uses LIFO (Last-In-First-Out) principle?",
                    new String[]{"Queue", "Stack", "Array", "Linked List"},
                    "Stack",
                    Subject.DSA
                ));
                break;
                
            case "EEE":
                defaultQuestions.add(new Question(
                    "What is the unit of electrical power?",
                    new String[]{"Ampere", "Volt", "Watt", "Ohm"},
                    "Watt",
                    Subject.POWER_SYSTEM
                ));
                break;
                
            case "CE":
                defaultQuestions.add(new Question(
                    "Which type of rock forms from the solidification of molten magma?",
                    new String[]{"Sedimentary", "Metamorphic", "Igneous", "None of these"},
                    "Igneous",
                    Subject.GEOLOGY
                ));
                break;
                
            case "ME":
                defaultQuestions.add(new Question(
                    "Which law of thermodynamics states that energy cannot be created or destroyed?",
                    new String[]{"Zeroth Law", "First Law", "Second Law", "Third Law"},
                    "First Law",
                    Subject.THERMODYNAMICS
                ));
                break;
        }

        saveQuestionsToFile(department, defaultQuestions);
    }

    private void loadAllQuestions() {
        File dir = new File(QUESTIONS_DIR);
        File[] departmentFiles = dir.listFiles((d, name) -> name.endsWith("_questions.txt"));

        if (departmentFiles != null) {
            for (File file : departmentFiles) {
                String department = file.getName().replace("_questions.txt", "");
                List<Question> questions = loadQuestionsFromFile(file);
                departmentQuestions.put(department, questions);
            }
        }
    }

    private List<Question> loadQuestionsFromFile(File file) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    questions.add(new Question(
                        parts[0], // question text
                        Arrays.copyOfRange(parts, 1, 5), // options
                        parts[5], // correct answer
                        Subject.fromString(parts[6]) // subject
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading questions from " + file.getName() + ": " + e.getMessage());
        }
        return questions;
    }

    public void addQuestion(String department, Question question) {
        List<Question> questions = departmentQuestions.getOrDefault(department, new ArrayList<>());
        questions.add(question);
        departmentQuestions.put(department, questions);
        saveQuestionsToFile(department, questions);
    }

    private void saveQuestionsToFile(String department, List<Question> questions) {
        File file = new File(QUESTIONS_DIR + "/" + department + "_questions.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Question q : questions) {
                writer.println(String.join("|",
                    q.getQuestion(),
                    q.getOptions()[0],
                    q.getOptions()[1],
                    q.getOptions()[2],
                    q.getOptions()[3],
                    q.getCorrectAnswer(),
                    q.getSubject().toString()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving questions to " + file.getName() + ": " + e.getMessage());
        }
    }

    public List<Question> getQuestionsByDepartmentAndSubject(String department, Subject subject) {
        List<Question> allDeptQuestions = departmentQuestions.getOrDefault(department, new ArrayList<>());
        return allDeptQuestions.stream()
                .filter(q -> q.getSubject() == subject)
                .collect(Collectors.toList());
    }

    public List<Question> getRandomQuestionsByDepartmentAndSubject(String department, Subject subject, int count) {
        List<Question> subjectQuestions = getQuestionsByDepartmentAndSubject(department, subject);
        Collections.shuffle(subjectQuestions);
        return subjectQuestions.stream().limit(count).collect(Collectors.toList());
    }
}