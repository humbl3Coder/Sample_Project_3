package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public abstract class CollectionLists {

    public static ObservableList<States> allStates = FXCollections.observableArrayList();
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    public static ObservableList<Physicians> allPhysicians = FXCollections.observableArrayList();
    public static ObservableList<Coverage> allCoverage = FXCollections.observableArrayList();
    public static ObservableList<StartHours> allStartHours = FXCollections.observableArrayList();
    public static ObservableList<EndHours> allEndHours = FXCollections.observableArrayList();
    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    public static ObservableList<Patients> allPatients = FXCollections.observableArrayList();

    public static ObservableList<States> getAllStates() {return allStates;}
    public static ObservableList<Users> getAllUsers() {return allUsers;}
    public static ObservableList<Physicians> getAllPhysicians() {return allPhysicians;}
    public static ObservableList<Coverage> getAllCoverage() {return allCoverage;}
    public static ObservableList<StartHours> getAllStartHours() {return allStartHours;}
    public static ObservableList<EndHours> getAllEndHours() {return allEndHours;}
    public static ObservableList<Appointments> getAllAppointments() {return allAppointments;}
    public static ObservableList<Patients> getAllPatients() {return allPatients;}

    public static void addPatient(Patients newPatient) {allPatients.add(newPatient);}

    public static void deletePatient(Patients selectedPatient) {getAllPatients().remove(selectedPatient);}

    public static void addAppointment(Appointments newAppointment) {allAppointments.add(newAppointment);}

    public static void deleteAppointment(Appointments selectedAppointment) {getAllAppointments().remove(selectedAppointment);}

    public static void addPhysician(Physicians newPhysician) {allPhysicians.add(newPhysician);}

    public static void addUser(Users newUser) {allUsers.add(newUser);}


    public static ObservableList<Patients> getInsured() {
        ObservableList<Patients> insPats = FXCollections.observableArrayList();

        for (Patients p : allPatients) {

            if (p instanceof Insured) {
                insPats.add(p);
            }
        }
        return insPats;
    }

    public static ObservableList<Patients> getUnInsured() {
        ObservableList<Patients> unPats = FXCollections.observableArrayList();

        for (Patients p : allPatients) {

            if (p instanceof Uninsured) {
                unPats.add(p);
            }
        }
        return unPats;
    }

    public static Patients lookupPat(int patID){
        for (Patients p : allPatients) {
            if (p.getPatID() == patID) {
                return p;
            }
        }
        return null;
    }

    public static ObservableList<Patients> lookupPat(String pat) {
        ObservableList<Patients> searchedPat = FXCollections.observableArrayList();
        ObservableList<Patients> searchPats = CollectionLists.getAllPatients();

        for(Patients p : searchPats) {
            if(p.getPatName().contains(pat) || p.getPatAdd().contains(pat) || p.getStateName().contains(pat) ||
                  p.getPatPos().contains(pat) || p.getPatPhone().contains(pat)) {
                searchedPat.add(p);
            }
        }
        return searchedPat;
    }

    public static ObservableList<States> getState (int sID) {
        ObservableList<States> filteredState =  FXCollections.observableArrayList();

        for (States d : allStates) {
            if (d.getsID() == sID)
                filteredState.add(d);
        }
        return filteredState;
    }

    public static ObservableList<Coverage> getCov (int covID) {
        ObservableList<Coverage> filteredCoverage =  FXCollections.observableArrayList();

        for (Coverage c : allCoverage) {
            if (c.getCovID() == covID)
                filteredCoverage.add(c);
        }
        return filteredCoverage;
    }


    public static ObservableList<Appointments> getWeekApp () {
        ObservableList<Appointments> weekApps = FXCollections.observableArrayList();

        for (Appointments w : allAppointments) {

            LocalDate startDate = w.getStart().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Locale locale = Locale.getDefault();

            if (startDate.get(WeekFields.of(locale).weekOfWeekBasedYear()) ==
                    currentDate.get(WeekFields.of(locale).weekOfWeekBasedYear())) {
                weekApps.add(w);
            }
        }
        return weekApps;
    }

    public static ObservableList<Appointments> getMonthApp() {
        ObservableList<Appointments> monthApps = FXCollections.observableArrayList();

        for (Appointments m : allAppointments) {

            if (m.getStart().getMonth() == LocalDate.now().getMonth()) {
                monthApps.add(m);
            }
        }
        return monthApps;
    }

    public static Appointments lookupApp(int appID){
        for (Appointments p : allAppointments) {
            if (p.getAppID() == appID) {
                return p;
            }
        }
        return null;
    }

    public static ObservableList<Appointments> lookupApp(String app) {
        ObservableList<Appointments> searchedApp = FXCollections.observableArrayList();
        ObservableList<Appointments> searchApps = CollectionLists.getAllAppointments();

        for(Appointments p : searchApps) {
            if(p.getTitle().contains(app) || p.getDesc().contains(app) || p.getPhyName().contains(app) ||
                   p.getLoc().contains(app) || p.getType().contains(app)){
                searchedApp.add(p);
            }
        }
        return searchedApp;
    }

    public static ObservableList<Patients> getPatient (int patID) {
        ObservableList<Patients> filteredPatient = FXCollections.observableArrayList();

        for (Patients p : allPatients) {
            if (p.getPatID() == patID)
                filteredPatient.add(p);
        }
        return filteredPatient;
    }

    public static ObservableList<Users> getUser (int useID) {
        ObservableList<Users> filteredUser = FXCollections.observableArrayList();

        for (Users u : allUsers) {
            if (u.getUseID() == useID)
                filteredUser.add(u);
        }
        return filteredUser;
    }

    public static ObservableList<Physicians> getPhysician (int phyID) {
        ObservableList<Physicians> filteredPhysician = FXCollections.observableArrayList();

        for (Physicians p : allPhysicians) {
            if (p.getPhysID() == phyID)
                filteredPhysician.add(p);
        }
        return filteredPhysician;
    }

    public static ObservableList<StartHours> getStart (LocalTime start) {
        ObservableList<StartHours> filteredStart = FXCollections.observableArrayList();
        for (StartHours s : allStartHours) {
            if (s.getStartLT() == start)
                filteredStart.add(s);
        }
        return filteredStart;
    }

    public static ObservableList<EndHours> getEnd (LocalTime end) {
        ObservableList<EndHours> filteredEnd = FXCollections.observableArrayList();
        for (EndHours e : allEndHours) {
            if (e.getEndLT() == end)
                filteredEnd.add(e);
        }
        return filteredEnd;
    }

    public static ObservableList<Appointments> getPhyApp (int phyID) {
        ObservableList<Appointments> phyApp = FXCollections.observableArrayList();

        for (Appointments p : allAppointments) {
            if (p.getPhyID() == phyID) {
                phyApp.add(p);
            }
        }
        return phyApp;
    }

    public static ObservableList<Appointments> getUseApp (int useID) {
        ObservableList<Appointments> useApp = FXCollections.observableArrayList();

        for (Appointments u : allAppointments) {
            if (u.getUseID() == useID) {
                useApp.add(u);
            }
        }
        return useApp;
    }
}
