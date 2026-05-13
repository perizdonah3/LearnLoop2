package com.periz.learnloop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.periz.learnloop.ui.screens.Splash.SplashScreen
import com.periz.learnloop.ui.screens.Home.HomeScreen
import com.periz.learnloop.ui.screens.Profile.ProfileScreen
import com.periz.learnloop.ui.screens.Settings.SettingsScreen
import com.periz.learnloop.ui.screens.Login.LoginScreen
import com.periz.learnloop.ui.screens.Register.RegisterScreen
import com.periz.learnloop.ui.screens.Intro.IntroScreen

import com.periz.learnloop.ui.screens.Planner.PlannerScreen
import com.periz.learnloop.ui.screens.NotesVault.NoteVaultScreen
import com.periz.learnloop.ui.screens.CareerGuidance.CareerHubScreen
import com.periz.learnloop.ui.screens.StudentCommunity.StudentCommunityScreen
import com.periz.learnloop.ui.screens.Wellness.WellnessScreen
import com.periz.learnloop.ui.screens.Achievements.AchievementScreen
import com.periz.learnloop.ui.screens.Notes.NotesScreen
import com.periz.learnloop.ui.screens.StudyStreak.StudyStreakScreen

import com.periz.learnloop.ui.screens.ProgressTracker.ProgressTrackerScreen
import com.periz.learnloop.ui.screens.Quiz.QuizScreen
import com.periz.learnloop.ui.screens.QuizResults.QuizResultsScreen
import com.periz.learnloop.ui.screens.StudyMaterials.IntentStudyMaterialsScreen
import com.periz.learnloop.ui.screens.StudyTimer.StudyTimerScreen
import com.periz.learnloop.ui.screens.Subject.SubjectScreen
import com.periz.learnloop.ui.screens.SubjectDetails.SubjectDetailsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_INTRO) {
            IntroScreen(navController)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }

        // 🏠 MAIN HOME
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        // 👤 BASIC SCREENS
        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }

        composable(ROUT_SETTINGS) {
            SettingsScreen(navController)
        }


        composable(ROUT_PLANNER) {
            PlannerScreen(navController)
        }

        composable(ROUT_NOTEVAULT) {
            NoteVaultScreen(navController)
        }


        composable(ROUT_NOTESSCREEN) {

            NotesScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(ROUT_STUDYMATERIALS) {
            IntentStudyMaterialsScreen(navController)
        }

        composable(ROUT_PROGRESSTRACKER) {
            ProgressTrackerScreen(navController)
        }

        composable(ROUT_QUIZ) {
            QuizScreen(navController)
        }

        composable(ROUT_QUIZRESULTS) {
            QuizResultsScreen(navController)
        }

        composable(ROUT_STUDYTIMER) {
            StudyTimerScreen(navController)
        }

        composable(ROUT_SUBJECT) {
            SubjectScreen(navController)
        }

        composable(ROUT_SUBJECTDETAILS) {
            SubjectDetailsScreen(navController)
        }

        composable(ROUT_CAREERGUIDANCE) {
            CareerHubScreen(navController)
        }

        composable(ROUT_STUDENTCOMMUNITY) {
            StudentCommunityScreen(navController)
        }

        composable(ROUT_WELLNESS) {
            WellnessScreen(navController)
        }

        composable(ROUT_ACHIEVEMENTS) {
            AchievementScreen(navController)
        }

        composable(ROUT_STUDYSTREAK) {
            StudyStreakScreen(navController)
        }
    }
}