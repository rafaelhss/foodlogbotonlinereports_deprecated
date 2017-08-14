package com.foodlog;

import com.foodlog.dayStats.DayStats;
import com.foodlog.dayStats.DayStatsService;
import com.foodlog.entity.MealLog;
import com.foodlog.entity.ScheduledMeal;
import com.foodlog.timeline.repository.ScheduledMealRepository;
import com.foodlog.timeline.service.MealLogDayService;
import com.foodlog.weight.Weight;
import com.foodlog.weight.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private WeightRepository weightRepository;

    @Autowired
    private MealLogDayService mealLogDayService;

    @Autowired
    private ScheduledMealRepository scheduledMealRepository;

    @Autowired
    private DayStatsService dayStatsService;


    @RequestMapping("/weight")
    public List<Weight> listWeights(@RequestParam(value="patient") String patient) {
        return weightRepository.findTop30ByOrderByWeightDateTimeDesc();
    }

    @RequestMapping("/meal-log")
    public List<MealLog> getAllMealLogDays() {
        return mealLogDayService.findAll();
    }

    @RequestMapping("/scheduled-meals")
    public List<ScheduledMeal> getAllScheduledMeals() {
        return scheduledMealRepository.findByOrderByTargetTime();
    }

    @RequestMapping("/day-stats")
    public DayStats getDayStats(){
        return dayStatsService.generateStats();
    }


}
