package solutions.entitiesandsessionbeans.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import solutions.entitiesandsessionbeans.ejb.Player;
import solutions.entitiesandsessionbeans.ejb.Team;
import solutions.entitiesandsessionbeans.ejb.TeamManagerLocal;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    @EJB
    private static TeamManagerLocal teamManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add some teams to the database.
        teamManager.addSomeTeams();

        // Get one team.
        Team team = teamManager.getTeamByName("Liverpool");
        System.out.println("Got Liverpool!");
        displayTeam(team);

        // Get all teams.
        List<Team> teams = teamManager.getAllTeams();
        System.out.println("Got all teams!");
        for (Team t: teams) {
            displayTeam(t);
        }

        // Modify a team.
        teamManager.modifyTeam("Super Swans");
        System.out.println("Modified Super Swans!");

        // Re-query the team, to prove it was saved correctly.
        team = teamManager.getTeamByName("Super Swans");
        displayTeam(team);
	}

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
    public void displayTeam(Team team) {

        System.out.printf("  %s, %s, %s\n", team.getName(), team.getGroundName(), team.getDivision());

        Collection<Player> players = team.getPlayers();
        if (players != null) {
            for (Player player: players) {
                System.out.printf("  Player: %s, %s, %d\n", player.getName(), player.getPosition(), player.getRating());
            }
        }
    }


}
