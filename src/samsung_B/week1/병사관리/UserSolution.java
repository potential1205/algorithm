package samsung_B.week1.병사관리;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class UserSolution {

    static class Sol implements Comparable<Sol>{
        int id;
        int team;
        int score;

        Sol(int id, int team, int score){
            this.id = id;
            this.team = team;
            this.score = score;
        }

        @Override
        public int compareTo(Sol o) {
            if(this.score != o.score){
                return Integer.compare(o.score, this.score);
            } else{
                return Integer.compare(o.id, this.id);
            }
        }
    }

    static HashMap<Integer,Sol> dataById = new HashMap<>();
    static HashMap<Integer, List<Sol>> dataByTeam = new HashMap<>();

    public void init() {}

    public void hire(int mID, int mTeam, int mScore) {
        Sol sol = new Sol(mID, mTeam, mScore);
        dataById.put(mID, sol);

        if(!dataByTeam.containsKey(mTeam)) {
            dataByTeam.put(mTeam, new ArrayList<>());
        }
        dataByTeam.get(mTeam).add(sol);
    }

    public void fire(int mID) {
        Sol sol = dataById.remove(mID);
        List<Sol> find = dataByTeam.get(sol.team);
        find.remove(sol);
        if (find.isEmpty()) {
            dataByTeam.remove(sol.team);
        }
    }

    public void updateSoldier(int mID, int mScore) {
        dataById.get(mID).score = mScore;
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        List<Sol> find = dataByTeam.get(mTeam);
        for(Sol sol : find){
            int score = sol.score + mChangeScore;
            if(score > 5){
                sol.score = 5;
            } else if (score<1) {
                sol.score = 1;
            } else{
                sol.score += mChangeScore;
            }
        }
    }

    public int bestSoldier(int mTeam) {
        List<Sol> find = dataByTeam.get(mTeam);
        Collections.sort(find);
        return find.get(0).id;
    }
}