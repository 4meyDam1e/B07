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
    //Create a Presenter using our mockMainAcitivity.
    private Presenter presenter = new Presenter(mockMainActivity);

    @Before
    public void setUp() {
        //Force out mockMainActivity to spit out the following.
        when(mockMainActivity.getEmail()).thenReturn("mock@email.com");
        when(mockMainActivity.getPassword()).thenReturn("mockPassword");
    }
    //-----------------------------------------------------------------------------------------------------------------------
    @Test
    public void testAttemptLoginWrongEmailFormat() {
        try (MockedStatic<InputChecker> mockInputChecker = mockStatic(InputChecker.class)) {
            //Force InputChecker to return the following when
            // given the specific email and password (which were also fixed by us in mockMainActivity).
            mockInputChecker.when(() -> InputChecker.checkEmail("mock@email.com")).thenReturn(false);
            mockInputChecker.when(() -> InputChecker.checkEmail("mockPassword")).thenReturn(true);

            presenter.attemptLogin();
            verify(mockMainActivity, times(1)).showMessage("The input format is wrong!");
        }
    }

    @Test
    public void testAttemptLoginWrongPasswordFormat() {
        try (MockedStatic<InputChecker> mockInputChecker = mockStatic(InputChecker.class)) {
            //Force InputChecker to return the following when
            // given the specific email and password (which were also fixed by us in mockMainActivity).
            mockInputChecker.when(() -> InputChecker.checkEmail("mock@email.com")).thenReturn(true);
            mockInputChecker.when(() -> InputChecker.checkEmail("mockPassword")).thenReturn(false);

            presenter.attemptLogin();
            verify(mockMainActivity, times(1)).showMessage("The input format is wrong!");
        }
    }
    //--------------------------------------------------------------------------------------------------------------------
    @Test
    public void testAttemptLoginWrongEmail() {
        DatabaseReference mockDBRef = mock(DatabaseReference.class);

        try (MockedStatic<FirebaseDatabase> mockFirebaseDatabase = mockStatic(FirebaseDatabase.class)) {
            mockFirebaseDatabase.when(() -> FirebaseDatabase.getInstance().getReference()
                            .child("Users").child(User.getID("mock@email.com"))).thenReturn(mockDBRef);

            mockDBRef.removeValue();
            presenter.attemptLogin();
            verify(mockMainActivity, times(1)).showMessage("The input format is wrong!");
        }
    }
}
