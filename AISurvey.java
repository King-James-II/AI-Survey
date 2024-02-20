import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
/*This AI program conducts a survey to guess a user's political party. It uses HashMaps to store 
 * questions, answers, and answer weights. After the survey, the program guesses the party based on
 *  weighted scores. It then asks the user for the actual party and updates answer weights 
 *  accordingly, saving the data to a text file for future use. */
public class AISurvey {

	static double [] scores = {0, 0, 0, 0};
	static Scanner scanner = new Scanner(System.in);
	    public static void main(String[] args) {
	    	Random random = new Random();
	    	int actparty;
	    	
	    	// Initialize variables to feed into question and answer hashmaps for easier editing 
	    	// and readability. 
	    	String Q1 = "Do you believe that the government should play a larger role in the "
	    			+ "economy?";
	    	String[] Q1ans = {"They should play a larger role", "They should play a smaller role", 
	    			"They should play an appropriate role", "They should play no role"};
	    	double[] Q1A1pts = {10,0,5,5};
	    	double[] Q1A2pts = {0,10,5,5};
	    	double[] Q1A3pts = {5,5,10,0};
	    	double[] Q1A4pts = {0,0,10,10};
	    	
	    	String Q2 = "Should the government provide more funding for public education?";
	    	String[] Q2ans = {"Yes, provide more funding for public education", 
	    			"No, It's too expensive.", 
	    			"Yes, only if it reforms the system to be more efficient", 
	    			"No, the government shouldn't be involved in education"};
	    	double[] Q2A1pts = {10,0,5,5};
	    	double[] Q2A2pts = {0,10,5,5};
	    	double[] Q2A3pts = {5,5,10,0};
	    	double[] Q2A4pts = {0,0,10,10};
	    	
	    	String Q3 = "Should the government regulate gun ownership?";
	    	String[] Q3ans = {"They should regulate gun ownership", 
	    			"They should not regulate gun ownership because it's a constitutional right", 
	    			"They should regulate it to some extent","They should not regulate it at all"};
	    	double[] Q3A1pts = {10,0,5,5};
	    	double[] Q3A2pts = {0,10,5,5};
	    	double[] Q3A3pts = {5,5,10,0};
	    	double[] Q3A4pts = {0,0,10,10};
	    	
	    	String Q4 = "Should the United States withdraw from foreign wars?";
	    	String[] Q4ans = {"Only fight in foreign wars to protect the U.S. from being attacked",
	    			"Only fight in foreign wars that are in best interest of the U.S.", 
	    			"We should not fight in any foreign wars at all", 
	    			"Only fight in foreign wars as a last resort, clear danger to U.S. or our "
	    			+ "allies"};
	    	double[] Q4A1pts = {10,0,5,5};
	    	double[] Q4A2pts = {5,10,5,5};
	    	double[] Q4A3pts = {0,10,0,5};
	    	double[] Q4A4pts = {5,5,5,5};
	    	
	    	String Q5 = "Should the government provide universal healthcare?";
	    	String[] Q5ans = {"Yes, provide healthcare to everyone", "No, It's too expensive", 
	    			"They should provide public healthcare but not required to participate", 
	    			"Government should not be involved in healthcare at all"};
	    	double[] Q5A1pts = {10,0,5,5};
	    	double[] Q5A2pts = {0,10,5,5};
	    	double[] Q5A3pts = {5,5,10,0};
	    	double[] Q5A4pts = {0,0,10,10};
	    	
	    	
	    	// Initialize HashMap to store questions and their corresponding answers.
	        Map<String, String[]> surveyQuestions = new HashMap<>();
	        surveyQuestions.put(Q1, Q1ans);
	        surveyQuestions.put(Q2, Q2ans);
	        surveyQuestions.put(Q3, Q3ans);
	        surveyQuestions.put(Q4, Q4ans);
	        surveyQuestions.put(Q5, Q5ans);

	        // Initializing The Answer Weights that correspond to each question.
	        // This will be used to guess a user's politcal party.
	        Map<String, double[]> answerPts = new HashMap<>();
	        answerPts.put(Q1ans[0], Q1A1pts);
	        answerPts.put(Q1ans[1], Q1A2pts);
	        answerPts.put(Q1ans[2], Q1A3pts);
	        answerPts.put(Q1ans[3], Q1A4pts);
	        answerPts.put(Q2ans[0], Q2A1pts);
	        answerPts.put(Q2ans[1], Q2A2pts);
	        answerPts.put(Q2ans[2], Q2A3pts);
	        answerPts.put(Q2ans[3], Q2A4pts);
	        answerPts.put(Q3ans[0], Q3A1pts);
	        answerPts.put(Q3ans[1], Q3A2pts);
	        answerPts.put(Q3ans[2], Q3A3pts);
	        answerPts.put(Q3ans[3], Q3A4pts);
	        answerPts.put(Q4ans[0], Q4A1pts);
	        answerPts.put(Q4ans[1], Q4A2pts);
	        answerPts.put(Q4ans[2], Q4A3pts);
	        answerPts.put(Q4ans[3], Q4A4pts);
	        answerPts.put(Q5ans[0], Q5A1pts);
	        answerPts.put(Q5ans[1], Q5A2pts);
	        answerPts.put(Q5ans[2], Q5A3pts);
	        answerPts.put(Q5ans[3], Q5A4pts);
	        
	        // Call the loadWeights method to retrieve previous updates based on user feedback.
	        loadWeights(answerPts, "answeights.txt");
	        
	        // Call takeSurvey method to survey the user using the HashMap Questions and answers.
	        // Storing the responses within this HashMap so we can retrieve the answer weights.
	        Map<String, String> surveyResponses = takeSurvey(surveyQuestions);

	        // Displaying the survey Questions and answers stored and adding up the scores
	        // related to each party.
	        System.out.println("\nSurvey Results:");
	        for (Map.Entry<String, String> entry : surveyResponses.entrySet()) {
	            String question = entry.getKey();
	            String answer = entry.getValue();
	            

	            scores[0] += answerPts.get(answer)[0];
	            scores[1] += answerPts.get(answer)[1];
	            scores[2] += answerPts.get(answer)[2];
	            scores[3] += answerPts.get(answer)[3];
	            System.out.println(question + ": " + answer);
	        }
	        // This will determine which political party scored highest 0:democratic, 1:republican,
	        // 2:libertarian, or 3:other
	        int tiebreaker = random.nextInt(2);
	        double dpoints = scores[0];
	        double largest = dpoints;
	        int party = 0;
	        for (int i = 0; i < scores.length; i++) {
	            if (scores[i] > largest) {
	                largest = scores[i];
	                party = i;
	            }
	            // Flips a coin if there is more than one score tied.
	            else if (scores[i] == largest) {
	            	if (tiebreaker == 0) {
	            		party = i;
	            	}
	            }
	        }
	        
	        


	        // Displays guess to the user based on input and highest party score value.
	        if (party == 0) {
	        	System.out.println("Based on your input we guess you're affiliated with the "
	        			+ "Democratic Party.");
	        }
	        else if (party  == 1) {
	        	System.out.println("Based on your input we guess you're affiliated with the "
	        			+ "Republican Party.");
	        }
	        else if (party == 2) {
	        	System.out.println("Based on your input we guess you're affiliated with the "
	        			+ "Libertarian Party.");
	        }
	        else {
	        	System.out.println("Based on your input we guess you're affiliated with another "
	        			+ "Party.");
	        }
	        
	        // Prompts user for which political party they identify with.
	        System.out.println("Which political Party are you Affiliated with?\n1. Democratic\n2. "
	        		+ "Republican\n3. Libertarian\n4. Other");
	        actparty = Integer.parseInt(scanner.nextLine())-1;
	        
	        // Update the weights of each answer to improve the likelihood of the program to guess
	        // correctly next time. This adds to the existing weight.
	        for (Map.Entry<String, String> entry : surveyResponses.entrySet()) {
	            String answer = entry.getValue();
	            double [] updateweights = answerPts.get(answer);
	            updateweights[0] = Math.round((updateweights[0]+0.1)*10.0)/10.0;
	            updateweights[1] = Math.round((updateweights[1]+0.1)*10.0)/10.0;
	            updateweights[2] = Math.round((updateweights[2]+0.1)*10.0)/10.0;
	            updateweights[3] = Math.round((updateweights[3]+0.1)*10.0)/10.0;
	            
	            for (int i = 0; i < updateweights.length; i++ ) {
	            	if (updateweights[i] > 10) {
	            		updateweights[i] = 10;
	            	}
	            	if (updateweights[i] < 0) {
	            		updateweights[i] = 0;
	            	}
	            }

	            // Put new weights back into HashMap and save it to the answeights text file for
	            // future program uses.
	            answerPts.put(answer, updateweights);
	            
	        }
	        saveWeights(answerPts, "answeights.txt");
	        System.out.println("\nThank you for taking the survey!");
	    }

	    // Survey method that prompts the user for each question and provides them options to 
	    // choose from. This is all sent back to the main method which stores the answers. 
	    public static Map<String, String> takeSurvey(Map<String, String[]> questions) {
	        
	        Map<String, String> responses = new HashMap<>();
	        System.out.println("Welcome to the Survey!");
	        for (Map.Entry<String, String[]> entry : questions.entrySet()) {
	            String question = entry.getKey();
	            String[] options = entry.getValue();

	            System.out.println(question);
	            for (int i = 0; i < options.length; i++) {
	                System.out.println((i + 1) + ". " + options[i]);
	            }

	            // Prompt user for input and reject invalid inputs.
	            int choice;
	            while (true) {
	                try {
	                    System.out.print("Enter your choice (1, 2, 3, etc.): ");
	                    choice = Integer.parseInt(scanner.nextLine());
	                    if (choice >= 1 && choice <= options.length) {
	                        responses.put(question, options[choice - 1]);
	                        break;
	                    } else {
	                        System.out.println("Invalid choice. Please try again.");
	                    }
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid input. Please enter a number.");
	                }
	            }
	        }
	        return responses;
	    }
	    
	    // Method saves weights from the provided HashMap to a specified text file.
	    public static void saveWeights(Map<String, double[]> hashMap, String filename) {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
	            for (Map.Entry<String, double[]> entry : hashMap.entrySet()) {
	                String answer = entry.getKey();
	                double[] weights = entry.getValue();
	                StringBuilder sb = new StringBuilder(answer);
	                sb.append("=");
	                for (int i = 0; i < weights.length; i++) {
	                    sb.append(weights[i]);
	                    if (i < weights.length - 1) {
	                        sb.append(";");
	                    }
	                }
	                writer.println(sb.toString());
	            }
	            System.out.println("Weights saved successfully."); 
	        } 
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	 // Method loads weights from the specified text file to the HashMap.
	    public static void loadWeights(Map<String, double[]> hashMap, String filename) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split("=");
	                if (parts.length == 2) {
	                    String answer = parts[0];
	                    String[] weightsStr = parts[1].split(";");
	                    double[] weights = new double[weightsStr.length];
	                    for (int i = 0; i < weightsStr.length; i++) {
	                        weights[i] = Double.parseDouble(weightsStr[i]);
	                    }
	                    hashMap.put(answer, weights);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
}
