/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quidditchsim;

/**
 *
 * @author ssingh
 */
import java.util.*;
public class QuidditchSim {

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args) {
       ArrayList<String> teams = new ArrayList();
       teams.add(0, "Pikachu");
       teams.add(1, "Snorlax");
       teams.add(2, "Mewtwo");
       teams.add(3, "Jigglypuff");
       teams.add(4, "Slowpoke");
       teams.add(5, "Jynx");
       teams.add(6, "Onix");
       teams.add(7, "Wigglytuff");
       
      //int roundCounter = 1;
      //int teamA = 0;
      // int teamB = 0;
      // Scanner scan = new Scanner(System.in);
      // System.out.println(teams);
      // String prompt ="";
      // prompt = "Enter team A index";
      // teamA = SafeInput.getRangedInt(scan, prompt, 0, teams.size()-1);
      // do
      // {
      //  prompt = "Enter team B index";
      //  teamB = SafeInput.getRangedInt(scan, prompt, 0, teams.size()-1);
      // }while(teamA == teamB);
       System.out.println("Round #1");
       System.out.println("=========================");
       teams =  playGame(0, 1, teams);
       // Mewtwo vs Jigglypuff
       System.out.println("=========================");
       teams =  playGame(1, 2, teams);
       // Slowpoke vs Jynx
       System.out.println("=========================");
       teams =  playGame(teams.size()-4, teams.size()-3, teams);
       // Onix vs Wigglytuff
       System.out.println("=========================");
       teams =  playGame(teams.size()-2, teams.size()-1, teams);
       System.out.println("Following teams left in the competition after Round 1");
       System.out.println(teams);
       System.out.println("\n Round #2");
       //first 2 teams out of remaining 4
       System.out.println("=========================");
       teams =  playGame(0, 1, teams);
       //last 2 teams out of 4
       System.out.println("=========================");
       teams =  playGame(teams.size()-2, teams.size()-1, teams);
       System.out.println("Following teams left in the competition after Round 2");
       System.out.println(teams);
        System.out.println("\n Round #3");
       //final 2 teams
       System.out.println("=========================");
       teams =  playGame(0, 1, teams);
       System.out.println(teams.get(0) + "Won the Tournament!!!");
    }
    
    public static ArrayList<String> playGame(int teamA, int teamB, ArrayList<String> teams)
    {
        String teamAName = teams.get(teamA);
        String teamBName = teams.get(teamB);
        Random rnd = new Random();
        boolean endGame = false;
        String winner = "";
        String teamRemoved ="";
        boolean isDeathQuarter = false;
        Integer teamAtotalScore = 0;
        Integer teamBtotalScore = 0; 
        int winnerScore = 0;
        int loserScore = 0; 
        boolean teamACaughtSnitch = false;
        boolean teamBCaughtSnitch = false;
        
        ArrayList<Integer> teamAScores = new ArrayList();
        ArrayList<Integer> teamBScores = new ArrayList();
        //4 quarters
       for(int q = 0; q < 4; q++ )
        {
            int teamAGoalsQ = rnd.nextInt(4)+1;
            int teamBGoalsQ = rnd.nextInt(4)+1;
            teamAScores.add(q, teamAGoalsQ * 10);        
            teamBScores.add(q, teamBGoalsQ * 10); 
            double chanceTeamA = rnd.nextDouble();
            double chanceTeamB = rnd.nextDouble();
            if (chanceTeamA <= 0.15)
            {
                teamACaughtSnitch = true;
                endGame = true;
                winner = teamAName;
            }
            if (!endGame && chanceTeamB <= 0.15)
            {
                teamBCaughtSnitch = true;
                endGame = true;
                winner = teamBName;
            }
            if(endGame)
            {
                break;
            }
        }
        
       teamAtotalScore = getTotalScore(teamAScores);
       teamBtotalScore = getTotalScore(teamBScores);
        
        if(winner.isEmpty() && teamAtotalScore.equals(teamBtotalScore))
        {
            int round = 4;
         while(winner.isEmpty())
         {
            isDeathQuarter = true;
            int teamAGoalsQ = rnd.nextInt(4)+1;
            int teamBGoalsQ = rnd.nextInt(4)+1;
            teamAScores.add(round, teamAGoalsQ * 10);        
            teamBScores.add(round, teamBGoalsQ * 10);
            teamAtotalScore = getTotalScore(teamAScores);
            teamBtotalScore = getTotalScore(teamBScores);
            if(!Objects.equals(teamAtotalScore, teamBtotalScore))
            {
                if(teamAtotalScore > teamBtotalScore)
                {
                    winner = teamAName;
                    
                }
                else if(teamBtotalScore > teamAtotalScore)
                {
                    winner = teamBName;
                   
                }
            }
            round++;
         }
        }
        else if(winner.isEmpty())
        {
                if(teamAtotalScore > teamBtotalScore)
                {
                    winner = teamAName;
                    
                }
                else if(teamBtotalScore > teamAtotalScore)
                {
                    winner = teamBName;
                   
                }
        }
        if(winner.equals(teamAName))
        {
            teams.remove(teamB);
            teamRemoved = teamBName;
            winnerScore = teamAtotalScore;
            loserScore = teamBtotalScore;
        }
        else 
        {
            teams.remove(teamA);
            teamRemoved = teamAName;
            winnerScore = teamBtotalScore;
            loserScore = teamAtotalScore;
        }
        System.out.println(teamAName + " vs "+ teamBName);
        System.out.println("-------------------------");
        for(int qtr = 0; qtr < teamAScores.size(); qtr++ )
        {
            if(qtr > 3)
            {
              System.out.print("\n D Qtr ");  
            }
            else
            {
                int qtrVal = qtr + 1;
             System.out.print("\n Qtr"+ qtrVal);
            }
            System.out.print("\t"+ teamAScores.get(qtr));
            System.out.print("\t"+ teamBScores.get(qtr));
            
        }
        if(teamACaughtSnitch || teamBCaughtSnitch)
        {
            System.out.println("\n"+winner+" caught snitch and Won. "+teamRemoved+" eliminated" );
        }
        else
        {
            System.out.println("\n"+winner+" Win "+winnerScore+ " to "+loserScore+" "+teamRemoved+" eliminated" );
        }
        System.out.println("-------------------------");
        return teams;
    }
    
    public static Integer getTotalScore(ArrayList<Integer> teamScores)
    {
        Integer sum = 0;
       for(Integer score: teamScores)
       {
        sum += score;
       }
       return sum;
    }
    
}

