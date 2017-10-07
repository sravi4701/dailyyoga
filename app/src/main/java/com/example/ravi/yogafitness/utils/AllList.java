package com.example.ravi.yogafitness.utils;

import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 03-10-2017.
 */

public class AllList {
    private List<Exercise> beginnerList = new ArrayList<>();
    private List<Exercise> intermediateList = new ArrayList<>();
    private List<Exercise> advanceList = new ArrayList<>();
    private List<Exercise> allList = new ArrayList<>();

    public List<Exercise> getAllList() {
        allList.addAll(getBeginnerList());
        allList.addAll(getIntermediateList());
        allList.addAll(getAdvanceList());
        return allList;
    }

    public List<Exercise> getBeginnerList() {
        setBeginnerList();
        return beginnerList;
    }

    public void setBeginnerList() {
        beginnerList.add(new Exercise(R.drawable.chair_pose_thumbnail,
                "Chair Pose",
                "Improves posture and balance\nTones abdomen and thighs\nGreat for athletes and runners"));

        beginnerList.add(new Exercise(R.drawable.cat_pose_thumbnail1,
                "Cat Pose",
                "\n" +
                        "Relaxes back and tones abdomen\n" +
                        "Aids in weight loss\n" +
                        "Rejuvenates body and mind\n"));

        beginnerList.add(new Exercise(R.drawable.chaturanga_dandasana_thumbnail,
                "Chaturanga Asana",
                "Strengthens toes and fingers\n" +
                        "Tones upper back, hips and chest\n" +
                        "Works great on balance and arm strength"));
        beginnerList.add(new Exercise(R.drawable.childs_pose_thumbnail,
                "Childs Pose",
                "Great for stress, depression, and anxiety\n" +
                        "Relaxes upper back, neck and arms\n" +
                        "Helps sleep better at night\n"));

        beginnerList.add(new Exercise(R.drawable.cow_pose_bitilasana_thumbnail,
                "Cow Pose",
                "Great restorative pose for lower back stiffness\n" +
                        "Strengthens arms and knees\n" +
                        "Good for students looking to enhance focus\n"));
        beginnerList.add(new Exercise(R.drawable.downward_facing_dog_pose_adho_mukha_svanasana_thumbnail,
                "Downward facing Dog",
                "Enhances hamstring flexibility and hip flexion\n" +
                        "Stretches Achilles’ tendons\n" +
                        "Strengthens wrists, ankles, toes and back\n"));
        beginnerList.add(new Exercise(R.drawable.padmasana_thumbnail,
                "Padmasana",
                "\n" +
                        "Ideal pose for meditation and Pranayama\n" +
                        "Stretches lower body and relaxes mind\n" +
                        "Helpful in back pain and other back issues\n"));
        beginnerList.add(new Exercise(R.drawable.seated_forward_bend_pose_paschimottanasana_thumbnail,
                "Paschimottanasan",
                "\n" +
                        "Stretches legs and back\n" +
                        "Stimulates abdominal organs\n" +
                        "Relaxes mind\n"));
        beginnerList.add(new Exercise(R.drawable.tadasana_mountain_pose_thumbnail,
                "Mountain Pose",
                "\n" +
                        "Helps gain initial sense of balance\n" +
                        "Opens up chest cavity for breathing exercises\n" +
                        "Stimulates thyroid and helps increase height\n"));
        beginnerList.add(new Exercise(R.drawable.tree_pose_vrksasana_thumbnail,
                "Tree Pose",
                "Helps gain better sense of balance\n" +
                        "Strengthens ankles, knees and improves toe grip\n" +
                        "Rectifies standing posture\n"));
        beginnerList.add(new Exercise(R.drawable.warrior_i_virabhadrasana_i_thumbnail,
                "Warrior Pose",
                "Shapes calves and hips\n" +
                        "Strengthens and stretches ankles and thighs\n" +
                        "Relaxes arms and back\n"));
    }

    public List<Exercise> getIntermediateList() {
        setIntermediateList();
        return intermediateList;
    }

    public void setIntermediateList() {
        intermediateList.add(new Exercise(R.drawable.bow_pose_dhanurasana_thumbnail,
                "Bow Pose",
                "Tones lower back, thighs and buttocks\nStretches quads, ankles and forearms\nOpens chest cavity and strengthens lungs"));

        intermediateList.add(new Exercise(R.drawable.big_toe_pose_padangusthasana_thumbnail,
                "Big Toe Pose",
                "Ideal for women who are trying to get pregnant\n" +
                        "Improves digestion\n" +
                        "Relieves headache\n"));
        intermediateList.add(new Exercise(R.drawable.boat_pose_thumbnail,
                "Boat Pose",
                "Helps get rid of belly fat\n" +
                        "Improves digestion\n" +
                        "Tones back, thighs, and arms\n"));
        intermediateList.add(new Exercise(R.drawable.camel_pose_thumbnail,
                "Camel Pose",
                "Strengthens knees and back\n" +
                        "Enhances balance and flexibility\n" +
                        "Improves concentration\n"));
        intermediateList.add(new Exercise(R.drawable.cobra_pose_thumbnail,
                "Cobra Pose",
                "\n" +
                        "Improves focus and retention\n" +
                        "Enhances cardiovascular capacity\n" +
                        "Tones abdomen and lower back\n"));
        intermediateList.add(new Exercise(R.drawable.crane_pose_bakasana_thumbnail,
                "Crane Pose",
                "\n" +
                        "Enhances arm and shoulders strength\n" +
                        "Great for spine and neck\n" +
                        "Improves digestion and balances hormones\n"));
        intermediateList.add(new Exercise(R.drawable.dolphin_pose_thumbnail,
                "Dolphin Pose",
                "Brings relief to asthma patients\n" +
                        "Stress-reliever\n" +
                        "Prevents osteoporosis\n"));
        intermediateList.add(new Exercise(R.drawable.eka_pada_svanasana_thumbnail,
                "Eka Pada Svanasan",
                "Enhances hamstring flexibility and hip flexion\n" +
                        "Stretches Achilles’ tendons and groin\n" +
                        "Improves digestion and balance\n"));
        intermediateList.add(new Exercise(R.drawable.half_bow_pose_thumbnail,
                "Half Bow Pose",
                "Improves balance and limb strength\n" +
                        "Increases joint health\n" +
                        "Deep stretches abdomen and thighs\n"));
        intermediateList.add(new Exercise(R.drawable.bow_pose_dhanurasana_thumbnail,
                "Dhanurasana",
                "Tones lower back, thighs and buttocks\n" +
                        "Stretches quads, ankles and forearms\n" +
                        "Opens chest cavity and strengthens lungs\n"));
        intermediateList.add(new Exercise(R.drawable.head_to_knee_pose_thumbnail,
                "Head to Knee Pose",
                "\n" +
                        "Great stretch for hamstrings and calves\n" +
                        "Increases metabolism\n" +
                        "Strengthens back and shoulders\n"));
        intermediateList.add(new Exercise(R.drawable.rabbit_pose_thumbnail,
                "Rabit Pose",
                "Beneficial in urinary and reproductive disorders\n" +
                        "Deep stretches shoulders, arms and wrists\n" +
                        "Strengthens knees\n"));
        intermediateList.add(new Exercise(R.drawable.revolved_triangle_pose_parivritta_trikonasana_thumbnail,
                "Triangle Pose",
                "\n" +
                        "Stretches inner thighs and groin\n" +
                        "Tones waist and aids in fat loss\n" +
                        "Helps with balance issues\n"));
        intermediateList.add(new Exercise(R.drawable.scale_pose_tolasana_thumbnail,
                "Scale Pose",
                "Strengthens arms, shoulders and chest\n" +
                        "Great for balance enhancement and hand grip\n" +
                        "Improves concentration\n"));
    }

    public List<Exercise> getAdvanceList() {
        setAdvanceList();
        return advanceList;
    }

    public void setAdvanceList() {
        advanceList.add(new Exercise(R.drawable.bound_angle_headstand_pose_thumbnail,
                "Head Stand Pose",
                "Relieves from depression, stress and anxiety\nStrengthens arms, wrists and shoulders\nImproves flexibility and balance"));
        advanceList.add(new Exercise(R.drawable.ardha_chakrasana_thumbnail,
                "Ardha chakrasana",
                "Strengthens and tones chest muscles\n" +
                        "Enhances sense of balance\n" +
                        "Increases ankle and back strength\n"));
        advanceList.add(new Exercise(R.drawable.compass_pose_thumbnail,
                "Compass pose",
                "\n" +
                        "Improves digestion\n" +
                        "Provides relief in respiratory disorders\n" +
                        "Develops patience\n"));
        advanceList.add(new Exercise(R.drawable.eka_pada_sirshasana_thumbnail,
                "Eka pada sirshasana",
                "Beneficial in mental stress, depression, anxiety and fatigue\n" +
                        "Stretches legs and ankles\n" +
                        "Strengthens back, elbows and forearms\n"));
        advanceList.add(new Exercise(R.drawable.firefly_pose_thumbnail,
                "Firefly pose",
                "\n" +
                        "Improves wrist strength and hand grip\n" +
                        "Tones back of thighs and abdomen\n" +
                        "Enhances balance and focus\n"));
        advanceList.add(new Exercise(R.drawable.head_stand_thumbnail,
                "Head stand",
                "Improves balance and focus\n" +
                        "Strengthens upper body\n" +
                        "Avoid if you have neck injury or migraine\n"));
        advanceList.add(new Exercise(R.drawable.karnapidasana_thumbnail,
                "Karnapidasana",
                "Improves balance\n" +
                        "Strengthens shoulders and knees\n" +
                        "Stretches back and feet\n"));
        advanceList.add(new Exercise(R.drawable.one_legged_wheel_pose_eka_pada_chakrasana_thumbnail,
                "One leg Wheel pose",
                "Improves balance and focus\n" +
                        "Strengthens arms, shoulders and ankles\n" +
                        "Tones back, hips and calves\n"));
        advanceList.add(new Exercise(R.drawable.king_pigeon_pose_thumbnail,
                "King Pigeon pose",
                "Ideal for those ladies who are trying to conceive\n" +
                        "Improves posture\n" +
                        "Relieves back pain\n"));
        advanceList.add(new Exercise(R.drawable.pecock_pose_thumbnail,
                "Pecock pose",
                "Good for working on focus\n" +
                        "Strengthens and tones arms and upper back\n" +
                        "Tones lower body and back\n"));
        advanceList.add(new Exercise(R.drawable.pinch_mayurasana_thumbnail,
                "Mayurasana",
                "Strengthens arms, shoulders and chest\n" +
                        "Tones back and buttocks\n" +
                        "Great for anxiety and stress\n"));
    }
}
