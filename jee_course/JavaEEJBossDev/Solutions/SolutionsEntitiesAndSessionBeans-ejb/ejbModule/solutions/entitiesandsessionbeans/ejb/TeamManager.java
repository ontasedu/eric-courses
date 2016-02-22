package solutions.entitiesandsessionbeans.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class TeamManager implements TeamManagerLocal {

    @PersistenceContext(name="SolutionsJPA-ejbPU")
    private EntityManager em;

    @Override
    public void addSomeTeams() {

        Team team1 = new Team();
        team1.setName("Super Swans");
        team1.setGroundName("Estadio Liberte");
        team1.setDivision("Premier League (and we're having a laugh!)");
        em.persist(team1);
        System.out.println("Just persisted " + team1.getName() + " with ID " + team1.getId());
        
        Team team2 = new Team();
        team2.setName("Arsenal");
        team2.setGroundName("The Emirates");
        team2.setDivision("Premier League");
        em.persist(team2);
        System.out.println("Just persisted " + team2.getName() + " with ID " + team2.getId());

        Team team3 = new Team();
        team3.setName("Cardiff City");
        team3.setGroundName("Legoland");
        team3.setDivision("Championship");
        em.persist(team3);
        System.out.println("Just persisted " + team3.getName() + " with ID " + team3.getId());
    }


    @Override
    public List<Team> getAllTeams() {

        Query query = em.createQuery(
           "SELECT t FROM Team AS t");

        // Execute the query, and get a collection of beans back.
        List<Team> teams = (List<Team>) query.getResultList();

        for (Team team: teams) {
            doDiagnostics("Got team in getAllTeams()", team);
        }

        return teams;
    }


    @Override
    public Team getTeamByName(String name) {

        Query query = em.createQuery(
           "SELECT t FROM Team AS t WHERE t.name = :name");
        query.setParameter("name", name);

        // Execute the query, and get a collection of beans back.
        Team team = null;
        try {
            team = (Team)query.getSingleResult();

        } catch (EntityNotFoundException ex) {
            System.out.println("Team not found: " + name);

        } catch (NonUniqueResultException ex) {
            System.out.println("More than one team named: " + name);

        }
        doDiagnostics("Got team in getTeamByName()", team);
        return team;
    }


    @Override
    public void modifyTeam(String name) {

        // Get the bean from the database.
        Team team = getTeamByName(name);

        // Change the team's division.
        team.setDivision("Premier League");

        // Add some players.
        Collection<Player> players = team.getPlayers();

        Player player1 = new Player();
        player1.setName("Torres");
        player1.setPosition("Striker");
        player1.setRating(5);
        players.add(player1);

        Player player2 = new Player();
        player2.setName("Gerrard");
        player2.setPosition("Midfield");
        player2.setRating(5);
        players.add(player2);

        doDiagnostics("New bean state in modifyTeam()", team);

        // No need to manually save in database, EM will do it automatically.
    }


    private void doDiagnostics(String message, Team team) {

        System.out.println(message);
        if (team == null) {
            System.out.print("Team is null");
        } else {
            System.out.println("Got " + team.getName() + ", player count is " + team.getPlayers().size());
        }
    }
}
