
import enums.Moves;
import gamecode.NewGameController;
import gamecode.SingleRoundController;
import org.junit.*;

import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserMoveReader;
import players.user.UserPlayer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RSPTestSuite {
    private final static UserMoveReader USER_MOVE_READER_MOCK = mock(UserMoveReader.class);
    private final CompPlayer compPlayer = new CompPlayer();
    private final UserController userController = new UserController();

    private static int TEST_COUNTER = 1;

    @Before
    public void before() {
        System.out.println("Start of test no " + TEST_COUNTER);
    }

    @After
    public void after() {
        System.out.println("End of test case.");
        TEST_COUNTER++;
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of test for RSP 2.0 game.....");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("All tests end.");
    }

    /* Test of ComPlayer class */
    @Test
    public void testSetComputerChances() {
        //Given
        when(USER_MOVE_READER_MOCK.readNumber()).thenReturn(1);
        compPlayer.setWinChancesModifier(USER_MOVE_READER_MOCK);
        when(USER_MOVE_READER_MOCK.readNumber()).thenReturn(2);
        compPlayer.setLoseChancesModifier(USER_MOVE_READER_MOCK);
        when(USER_MOVE_READER_MOCK.readNumber()).thenReturn(3);
        compPlayer.setDrawChancesModifier(USER_MOVE_READER_MOCK);
        int win = 0;
        int lose = 0;
        int draw = 0;
        //When
        compPlayer.setComputerChances(Moves.P);
        for (Moves move : compPlayer.getChoiceList()) {
            if (move.equals(Moves.S)) {
                win++;
            } else if (move.equals(Moves.R)) {
                lose++;
            } else {
                draw++;
            }
        }
        //Then
        Assert.assertTrue(win == 1 && lose == 2 && draw == 3);
    }

    /* Test of UserPlayer class. */
    @Test
    public void testMakeMoveRock() {
        //Given
        final UserPlayer userPlayer = new UserPlayer("name");
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("r");
        //When
        final Moves playerMove = userPlayer.makeMove(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertEquals(Moves.R, playerMove);
    }

    @Test
    public void testMakeMovePaper() {
        //Given
        final UserPlayer userPlayer = new UserPlayer("name");
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("p");
        //When
        final Moves playerMove = userPlayer.makeMove(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertEquals(Moves.P, playerMove);
    }

    @Test
    public void testMakeMoveScissors() {
        //Given
        final UserPlayer userPlayer = new UserPlayer("name");
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("s");
        //When
        final Moves playerMove = userPlayer.makeMove(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertEquals(Moves.S, playerMove);
    }

    /* Test of UserController class. */
    @Test
    public void testCreateFirstPlayer() {
        //Given
        final String firsPlayerName = "firstPlayer";
        final UserPlayer firstUserPlayer = new UserPlayer(firsPlayerName);
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn(firsPlayerName);
        //When
        userController.createFirstPlayer(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertEquals(firsPlayerName, userController.getCurrentPlayer().getName());
        Assert.assertTrue(userController.getPlayersSet().contains(firstUserPlayer));
    }

    @Test
    public void testCreateNewPlayer() {
        final String newPlayerName = "newPlayerName";
        final UserPlayer newPlayer = new UserPlayer(newPlayerName);

        when(USER_MOVE_READER_MOCK.readMove()).thenReturn(newPlayerName);
        //When
        userController.createNewPlayer(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertEquals(userController.getCurrentPlayer(), newPlayer);
        Assert.assertTrue(userController.getPlayersSet().contains(newPlayer));
    }

    @Test
    public void testChangeCurrentPlayerForExistingOne() {
        //Given
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("player1");
        userController.createNewPlayer(USER_MOVE_READER_MOCK);
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("player2");
        userController.createNewPlayer(USER_MOVE_READER_MOCK);

        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("player1");
        //When
        userController.changeCurrentPlayer(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertEquals(new UserPlayer("player1"), userController.getCurrentPlayer());
    }

    @Test
    public void testChangeCurrentPlayerForNonExistingOne() {
        //Given
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("player1");
        userController.createNewPlayer(USER_MOVE_READER_MOCK);
        final UserPlayer player2 = new UserPlayer("player2");
        //When
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("player2");
        userController.changeCurrentPlayer(USER_MOVE_READER_MOCK);
        //Then
        Assert.assertFalse(player2.equals(userController.getCurrentPlayer()));
        Assert.assertFalse(userController.getPlayersSet().contains(player2));
    }

    /* Test of SingleRoundController class */
    @Test
    public void testPlaySingleRoundPlayerWinsResult() {
        //Given
        final SingleRoundController singleRoundController = new SingleRoundController();
        final CompPlayer compPlayerMock = mock(CompPlayer.class);

        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("r");
        when(compPlayerMock.makeMove()).thenReturn(Moves.S);
        // When
        singleRoundController.playSingleRound(compPlayerMock, userController, USER_MOVE_READER_MOCK);
        //Then
        Assert.assertTrue(1 == userController.getCurrentPlayer().getRoundPoints());
        Assert.assertTrue(0 == compPlayerMock.getRoundPoints());
    }

    @Test
    public void testPlaySingleRoundPlayerLoseResult() {
        //Given
        final SingleRoundController singleRoundController = new SingleRoundController();
        final CompPlayer compPlayerMock = mock(CompPlayer.class);
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("s");
        when(compPlayerMock.makeMove()).thenReturn(Moves.R);
        // When
        singleRoundController.playSingleRound(compPlayerMock, userController, USER_MOVE_READER_MOCK);
        //Then
        Assert.assertTrue(0 == userController.getCurrentPlayer().getRoundPoints());
    }

    @Test
    public void testPlaySingleRoundDrawResult() {
        //Given
        final SingleRoundController singleRoundController = new SingleRoundController();
        final CompPlayer compPlayerMock = mock(CompPlayer.class);
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("s");
        when(compPlayerMock.makeMove()).thenReturn(Moves.S);
        // When
        singleRoundController.playSingleRound(compPlayerMock, userController, USER_MOVE_READER_MOCK);
        //Then
        Assert.assertTrue(0 == userController.getCurrentPlayer().getRoundPoints());
    }

    /* Test of NewGameController class. */
    @Test
    public void testPlayNewGamePlayerWins() {
        //Given
        final CompPlayer compPlayerMock = mock(CompPlayer.class);
        final NewGameController newGameController = new NewGameController();
        when(USER_MOVE_READER_MOCK.readNumber()).thenReturn(1);
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("r");
        when(compPlayerMock.makeMove()).thenReturn(Moves.S);
        //When
        newGameController.playNewGame(compPlayerMock, userController, USER_MOVE_READER_MOCK);
        //Then
        Assert.assertTrue(1 == userController.getCurrentPlayer().getWonGames());
    }
    @Test
    public void testPlayNewGamePlayerLoses() {
        //Given
        final CompPlayer compPlayerMock = mock(CompPlayer.class);
        final NewGameController newGameController = new NewGameController();
        when(USER_MOVE_READER_MOCK.readNumber()).thenReturn(1);
        when(USER_MOVE_READER_MOCK.readMove()).thenReturn("s");
        when(compPlayerMock.makeMove()).thenReturn(Moves.R);
        when(compPlayerMock.getRoundPoints()).thenReturn(0).thenReturn(1);
        //When
        newGameController.playNewGame(compPlayerMock, userController, USER_MOVE_READER_MOCK);
        //Then
        Assert.assertTrue(1 == userController.getCurrentPlayer().getLostGames());
    }
}