package com.example.health;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

import com.example.health.Classes.InputChecker;
import com.example.health.Classes.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PresenterUnitTests {
    private MainActivity mockMainActivity = mock(MainActivity.class);
    private FirebaseDatabase mockFirebaseDatabase = mock(FirebaseDatabase.class);
    //Create a Presenter using our mockMainAcitivity and mockFirebaseDatabase.
    private Presenter presenter = new Presenter(mockMainActivity, mockFirebaseDatabase);

    @Before
    public void setUp() {

    }
    //-----------------------------------------------------------------------------------------------------------------------
    @Test
    public void testAttemptLoginWrongEmailFormat() {
        //Force out mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mockEmail"); //Doesn't have @ and .
        when(mockMainActivity.getPassword()).thenReturn("mockGoodPassword");

        presenter.attemptLogin();
        verify(mockMainActivity, times(1)).showMessage("The input format is wrong!");
    }

    @Test
    public void testAttemptLoginWrongPasswordFormat() {
        //Force out mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mock@goodEmail.com");
        when(mockMainActivity.getPassword()).thenReturn("Pwd"); //Less than 6 characters long

        presenter.attemptLogin();
        verify(mockMainActivity, times(1)).showMessage("The input format is wrong!");
    }
    //--------------------------------------------------------------------------------------------------------------------
    @Test
    public void testAttemptLoginWrongEmail() {
        //Force our mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mock@email.com");
        when(mockMainActivity.getPassword()).thenReturn("mockPassword");

        //Create a mock DatabaseReference called mockUserRef, that has value null.
        DatabaseReference mockUserRef = mock(DatabaseReference.class);
        mockUserRef.removeValue();

        //Create a mock DatabaseReference called mockAllUsersRef, which is parent of mockUserRef.
        DatabaseReference mockAllUsersRef = mock(DatabaseReference.class);
        when(mockAllUsersRef.child(User.getID("mock@email.com"))).thenReturn(mockUserRef);

        //Create a mock DatabaseReference called mockRootRef.
        DatabaseReference mockRootRef = mock(DatabaseReference.class);
        when(mockRootRef.child("Users")).thenReturn(mockAllUsersRef);

        //Force our mock FirebaseDatabase to return mockDBRef when called with the hardcoded email().
        when(mockFirebaseDatabase.getReference()).thenReturn(mockRootRef);

        presenter.attemptLogin();
        verify(mockMainActivity, times(1)).showMessage("Incorrect Email or Password!");
    }
}
