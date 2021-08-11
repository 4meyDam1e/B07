package com.example.health;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import android.content.SharedPreferences;

import com.example.health.Classes.InputChecker;
import com.example.health.Classes.Patient;
import com.example.health.Classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPresenterUnitTests {
    private MainActivity mockMainActivity = mock(MainActivity.class);

    //Create a MainPresenter using our mockMainAcitivity and mockFirebaseDatabase.
    private MainPresenter mainPresenter = new MainPresenter(mockMainActivity);
    private MainPresenter spyMainPresenter = spy(mainPresenter);

    //-----------------------------------------------------checkLoginRecord Tests----------------------------------------------------------------
    @Test
    public void testCheckLoginRecordBlankEmail() {
        SharedPreferences mockSharedPreferences = mock(SharedPreferences.class);
        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getString("email", "")).thenReturn("");

        mainPresenter.checkLoginRecord();
        verify(spyMainPresenter, never()).successfullyLogin("");
    }

    @Test
    public void testCheckLoginRecordNonBlankEmail() {
        SharedPreferences mockSharedPreferences = mock(SharedPreferences.class);
        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getString("email", "")).thenReturn("nonBlankEmail");

        mainPresenter.checkLoginRecord();
        verify(spyMainPresenter, times(1)).successfullyLogin("nonBlankEmail");
    }

    //-------------------------------------------------------attemptLogin() Tests----------------------------------------------------------------
    @Test
    public void testAttemptLoginWrongEmailFormat() {
        //Force out mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mockEmail"); //Doesn't have @ and .
        when(mockMainActivity.getPassword()).thenReturn("mockGoodPassword");

        mainPresenter.attemptLogin();
        verify(mockMainActivity, times(1)).setEmailLayoutError("Please Enter a Valid Email!");
    }

    @Test
    public void testAttemptLoginWrongPasswordFormat() {
        //Force out mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mock@goodEmail.com");
        when(mockMainActivity.getPassword()).thenReturn("Pwd"); //Less than 6 characters long

        mainPresenter.attemptLogin();
        verify(mockMainActivity, times(1)).setPasswordLayoutError("Please Enter a Valid Password!");
    }

//    @Test
//    public void testAttemptLoginCorrectFormat() {
//        //Force out mockMainActivity to spit out the following.
//        when(mockMainActivity.getEmail()).thenReturn("mock@goodEmail.com"); //Doesn't have @ and .
//        when(mockMainActivity.getPassword()).thenReturn("mockGoodPassword");
//
//        MainModel mockMainModel = mock(MainModel.class);
//
//        mainPresenter.attemptLogin();
//        verify(mockMainModel, times(1)).checkEmailPassword("mock@goodEmail.com", "mockGoodPassword");
//    }
}
