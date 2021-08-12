package com.example.health;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Spy;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import android.content.Intent;
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

    private MainPresenter mainPresenter = new MainPresenter(mockMainActivity);
    private MainPresenter spyMainPresenter = spy(mainPresenter);

    private SharedPreferences mockSharedPreferences = mock(SharedPreferences.class);

    //-----------------------------------------------------checkLoginRecord() Tests----------------------------------------------------------------
    @Test
    public void testCheckLoginRecordBlankEmail() {
        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getString("email", "")).thenReturn("");

        mainPresenter.checkLoginRecord();
        verify(spyMainPresenter, never()).successfullyLogin("");
    }

    @Test
    public void testCheckLoginRecordNonBlankEmail() {
        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.getString("email", "")).thenReturn("nonBlankEmail");

        mainPresenter.checkLoginRecord();
        verify(spyMainPresenter, times(1)).successfullyLogin("nonBlankEmail");
    }

    //-------------------------------------------------------setLoginRecord() Tests----------------------------------------------------------------
    @Test
    public void testSetLoginRecord() {
        //Setting up mocks for lines: SharedPreferences settings = view.getSharedPreferences("setting", 0);
        //                            settings.edit().putString("email", email).commit();
        // in setLoginRecord()
        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);

        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);

        SharedPreferences.Editor mockPutString = mock(SharedPreferences.Editor.class);
        when(mockEditor.putString("email", "mock@email.com")).thenReturn(mockPutString);

        mainPresenter.setLoginRecord("mock@email.com");
        verify(mockPutString, times(1)).commit();
    }

    //------------------------------------------------------successfullyLogin() Tests---------------------------------------------------------------
//    @Test
//    public void testSuccessfullyLogin() {
//        //Setting up mocks for lines: SharedPreferences settings = view.getSharedPreferences("setting", 0);
//        //                            settings.edit().putString("email", email).commit();
//        // in setLoginRecord()
//        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);
//
//        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class);
//        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
//
//        SharedPreferences.Editor mockPutString = mock(SharedPreferences.Editor.class);
//        when(mockEditor.putString("email", "mock@email.com")).thenReturn(mockPutString);
//
//        //ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
//
//        mainPresenter.successfullyLogin("mock@email.com");
//
//        verify(spyMainPresenter, times(1)).setLoginRecord("mock@email.com");
//
//        Intent intent = new Intent(mockMainActivity, DashboardActivity.class);
//        doNothing().when(intent.putExtra("email", "mock@email.com"));
//
//        verify(intent).putExtra("email", "mock@email.com");
//        verify(mockMainActivity, times(1)).startActivity(intent);
////        //Checking lines: Intent intent = new Intent(this.view, DashboardActivity.class);
////        //                intent.putExtra("email", email);
////        //                this.view.startActivity(intent);
////        verify(mockMainActivity, times(1)).startActivity(intentCaptor.capture());
////        Intent testIntent = intentCaptor.getValue();
////        assertTrue(testIntent.hasExtra("email"));
////        assertEquals("mock@email.com", testIntent.getStringExtra("email"));
//
//        verify(mockMainActivity, times(1)).finish();
//    }

    //-------------------------------------------------------loginEmailError() Tests----------------------------------------------------------------
    @Test
    public void testLoginEmailError() {
        mainPresenter.loginEmailError();
        verify(mockMainActivity, times(1)).setEmailLayoutError("User doesn't exist!");
    }

    //-----------------------------------------------------loginPasswordError() Tests--------------------------------------------------------------
    @Test
    public void testLoginPasswordError() {
        mainPresenter.loginPasswordError();
        verify(mockMainActivity, times(1)).setPasswordLayoutError("Password incorrect!");
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

    //-------------------------------------------------------openRegisterPage() Tests------------------------------------------------------------
//    @Test
//    public void testOpenRegisterPage() {
//        Intent intent = new Intent(mockMainActivity, RegisterActivity.class);
//
//        mainPresenter.openRegisterPage();
//        verify(mockMainActivity).startActivity(intent);
//    }
}
