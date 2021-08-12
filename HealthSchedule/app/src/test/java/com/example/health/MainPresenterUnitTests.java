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
    private MainModel mockMainModel = mock(MainModel.class);

    private MainPresenter mainPresenter = new MainPresenter();
    private MainPresenter spyMainPresenter = spy(mainPresenter);

    private SharedPreferences mockSharedPreferences = mock(SharedPreferences.class);

    private Intent mockIntent = mock(Intent.class);

    @Before
    public void setUpCommon() {
        mainPresenter.setView(mockMainActivity);
        spyMainPresenter.setView(mockMainActivity);
        mainPresenter.setModel(mockMainModel);
        spyMainPresenter.setModel(mockMainModel);

        when(mockMainActivity.getIntent()).thenReturn(mockIntent);
        when(mockMainActivity.getSharedPreferences("setting", 0)).thenReturn(mockSharedPreferences);
    }

    /**Setting up mocks for the following lines in setLoginRecord():
     * SharedPreferences settings = view.getSharedPreferences("setting", 0);
     * settings.edit().putString("email", email).commit();
     **/
    private void setUpSharedPreferences(SharedPreferences.Editor mockEditor, SharedPreferences.Editor mockPutString, String email) {
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putString("email", email)).thenReturn(mockPutString);
    }

    //-----------------------------------------------------checkLoginRecord() Tests----------------------------------------------------------------
    @Test
    public void testCheckLoginRecordBlankEmail() {
        when(mockSharedPreferences.getString("email", "")).thenReturn("");

        //mainPresenter.checkLoginRecord();
        spyMainPresenter.checkLoginRecord();
        verify(spyMainPresenter, never()).successfullyLogin("");
    }

    @Test
    public void testCheckLoginRecordNonBlankEmail() {
        when(mockSharedPreferences.getString("email", "")).thenReturn("nonBlankEmail");

        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class);
        SharedPreferences.Editor mockPutString = mock(SharedPreferences.Editor.class);
        setUpSharedPreferences(mockEditor, mockPutString, "nonBlankEmail");

        //mainPresenter.checkLoginRecord();
        spyMainPresenter.checkLoginRecord();
        verify(spyMainPresenter, times(1)).successfullyLogin("nonBlankEmail");
    }

    //-------------------------------------------------------setLoginRecord() Tests----------------------------------------------------------------
    @Test
    public void testSetLoginRecord() {
        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class);
        SharedPreferences.Editor mockPutString = mock(SharedPreferences.Editor.class);
        setUpSharedPreferences(mockEditor, mockPutString, "mock@email.com");

        mainPresenter.setLoginRecord("mock@email.com");
        verify(mockPutString, times(1)).commit();
    }

    //------------------------------------------------------successfullyLogin() Tests---------------------------------------------------------------
    @Test
    public void testSuccessfullyLogin() {
        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class);
        SharedPreferences.Editor mockPutString = mock(SharedPreferences.Editor.class);
        setUpSharedPreferences(mockEditor, mockPutString, "mock@email.com");

        //mainPresenter.successfullyLogin("mock@email.com");
        spyMainPresenter.successfullyLogin("mock@email.com");

        verify(spyMainPresenter, times(1)).setLoginRecord("mock@email.com");
        verify(mockIntent).putExtra("email", "mock@email.com");
        verify(mockMainActivity).startActivity(mockIntent);
        verify(mockMainActivity).finish();
    }

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

    @Test
    public void testAttemptLoginCorrectFormat() {
        //Force out mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mock@goodEmail.com"); //Doesn't have @ and .
        when(mockMainActivity.getPassword()).thenReturn("mockGoodPassword");

        mainPresenter.attemptLogin();
        verify(mockMainModel, times(1)).checkEmailPassword("mock@goodEmail.com", "mockGoodPassword");
    }

    //-------------------------------------------------------openRegisterPage() Tests------------------------------------------------------------
//    @Test
//    public void testOpenRegisterPage() {
//        Intent intent = new Intent(mockMainActivity, RegisterActivity.class);
//
//        mainPresenter.openRegisterPage();
//        verify(mockMainActivity).startActivity(intent);
//    }
}
