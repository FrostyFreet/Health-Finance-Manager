package org.example.financemanagerbe.Service;

import org.example.financemanagerbe.DTO.WorkoutDto;
import org.example.financemanagerbe.Model.User;
import org.example.financemanagerbe.Model.Workout;
import org.example.financemanagerbe.Repository.UserRepository;
import org.example.financemanagerbe.Repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {


    private final WorkoutRepository workoutRepository;
    private final Utility utility;

    @Autowired
    public WorkoutService (WorkoutRepository workoutRepository, Utility utility){
        this.workoutRepository = workoutRepository;
        this.utility = utility;
    }
    public String createWorkOut(Workout workout){
        try{
            User currentUser = utility.getCurrentUser();

            Workout savedWorkout = new Workout();
            savedWorkout.setTitle(workout.getTitle());
            savedWorkout.setDuration(workout.getDuration());
            savedWorkout.setUser(currentUser);

            workoutRepository.save(savedWorkout);
            return "Created";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<WorkoutDto> getAllWorkouts(){
        User currentUser = utility.getCurrentUser();
        List<Workout> workoutList = workoutRepository.findAllByUser(currentUser);
        return workoutList.stream().map(WorkoutDto::new).toList();
    }

    public WorkoutDto getWorkOutById(Long id){
        return new WorkoutDto(workoutRepository.findById(id).orElseThrow(()-> new RuntimeException("No workout found!")));
    }

    public String deleteWorkOutById(Long id){
        try{
            Workout foundWorkout = workoutRepository.findById(id).orElseThrow(()-> new RuntimeException("No workout found!"));
            workoutRepository.delete(foundWorkout);
            return "Deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteWorkOutByName(String title){
        try{
            Workout foundWorkout = workoutRepository.findByTitle(title).orElseThrow(()-> new RuntimeException("No workout found!"));
            workoutRepository.delete(foundWorkout);
            return "Deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
