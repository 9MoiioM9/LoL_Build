package com.lol_build.api;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private long gameDuration;
    private long gameId;
    private String gameMode;
    private String gameName;
    private String gameType;
    private List<Participant> participants;
    private String platformId;
    private List<Team> teams;

    public long getGameDuration() {
        return gameDuration;
    }

    public long getGameId() {
        return gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public String getPlatformId() {
        return platformId;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public static class Participant {
        private int champLevel;
        private int championId;
        private String championName;
        private int deaths;
        private int item0;
        private int item1;
        private int item2;
        private int item3;
        private int item4;
        private int item5;
        private int item6;
        private int kills;
        private int magicDamageDealtToChampions;
        private int participantId;
        private int pentaKills;
        private int physicalDamageDealtToChampions;
        private int profileIcon;
        private String puuid;
        private String riotIdGameName;
        private String riotIdTagline;
        private int summonerId;
        private int summonerLevel;
        private int teamId;
        private String teamPosition;
        private int totalDamageDealtToChampions;
        private int totalMinionsKilled;
        private int trueDamageDealtToChampions;
        private int visionScore;
        private boolean win;

        public int getChampLevel() {
            return champLevel;
        }

        public int getChampionId() {
            return championId;
        }

        public String getChampionName() {
            return championName;
        }

        public int getDeaths() {
            return deaths;
        }

        public int getItem0() {
            return item0;
        }

        public int getItem1() {
            return item1;
        }

        public int getItem2() {
            return item2;
        }

        public int getItem3() {
            return item3;
        }

        public int getItem4() {
            return item4;
        }

        public int getItem5() {
            return item5;
        }

        public int getItem6() {
            return item6;
        }

        public int getKills() {
            return kills;
        }

        public int getMagicDamageDealtToChampions() {
            return magicDamageDealtToChampions;
        }

        public int getParticipantId() {
            return participantId;
        }

        public int getPentaKills() {
            return pentaKills;
        }

        public int getPhysicalDamageDealtToChampions() {
            return physicalDamageDealtToChampions;
        }

        public int getProfileIcon() {
            return profileIcon;
        }

        public String getPuuid() {
            return puuid;
        }

        public String getRiotIdGameName() {
            return riotIdGameName;
        }

        public String getRiotIdTagline() {
            return riotIdTagline;
        }

        public int getSummonerId() {
            return summonerId;
        }

        public int getSummonerLevel() {
            return summonerLevel;
        }

        public int getTeamId() {
            return teamId;
        }

        public String getTeamPosition() {
            return teamPosition;
        }

        public int getTotalDamageDealtToChampions() {
            return totalDamageDealtToChampions;
        }

        public int getTotalMinionsKilled() {
            return totalMinionsKilled;
        }

        public int getTrueDamageDealtToChampions() {
            return trueDamageDealtToChampions;
        }

        public int getVisionScore() {
            return visionScore;
        }

        public boolean isWin() {
            return win;
        }
        public String getKDA(){
            return kills+"/"+deaths;
        }
        public List<Integer> getItems(){
            List<Integer> items = new ArrayList<>();
            items.add(item0);
            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);
            items.add(item5);
            items.add(item6);
            return items;
        }
    }

    public static class Team {
        private List<Ban> bans;
        private Objectives objectives;
        private int teamId;
        private boolean win;

        public List<Ban> getBans() {
            return bans;
        }

        public Objectives getObjectives() {
            return objectives;
        }

        public int getTeamId() {
            return teamId;
        }

        public boolean isWin() {
            return win;
        }

        public static class Ban {
            private int championId;
            private int pickTurn;

            public int getChampionId() {
                return championId;
            }

            public int getPickTurn() {
                return pickTurn;
            }
        }

        public static class Objectives {
            private Objective champion;

            public static class Objective {
                private boolean first;
                private int kills;

                public int getKills() {
                    return kills;
                }
            }

            public Objective getChampion() {
                return champion;
            }

            public int getAllKills(){
                return champion.getKills();
            }
        }

    }

}
