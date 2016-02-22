package solutions.entitiesandsessionbeans.ejb;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TeamManagerLocal {
    public void addSomeTeams();
    public List<Team> getAllTeams();
    public Team getTeamByName(String name);
    public void modifyTeam(String name);
}
