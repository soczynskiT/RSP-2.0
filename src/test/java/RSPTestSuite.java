import enums.Moves;
import gamecode.GameLogicController;
import org.junit.*;
import players.computer.CompPlayer;
import players.user.UserController;
import players.user.UserPlayer;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RSPTestSuite {
    final private GameLogicController gameLogicController = new GameLogicController();
    final private UserPlayer player = new UserPlayer("name");
    final private UserController userController = new UserController(player);
    final private CompPlayer compPlayer = new CompPlayer();

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

    /* Test of GameLogicController Class */
    @Test
    public void testClearCurrentRoundPoints() {
        //Given
        player.setRoundPoints(5);
        compPlayer.setRoundPoints(5);

        //When
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testSetNumberOfRoundsToWin() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        final int expectedResult = 5;
        final ByteArrayInputStream stream = new ByteArrayInputStream("5".getBytes());
        final Scanner testScanner = new Scanner(stream);

        //When
        final int result = gameLogicController.setNumberOfRoundsToWIn(testScanner);

        //Then
        Assert.assertTrue(expectedResult == result);
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerRockCompRock() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.R;
        final Moves compMove = Moves.R;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerRockCompPaper() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.R;
        final Moves compMove = Moves.P;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 1 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerRockCompScissors() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.R;
        final Moves compMove = Moves.S;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(1 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerPaperCompRock() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.P;
        final Moves compMove = Moves.R;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(1 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerPaperCompScissors() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.P;
        final Moves compMove = Moves.S;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 1 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerPaperCompPaper() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.P;
        final Moves compMove = Moves.P;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerScissorsCompRock() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.S;
        final Moves compMove = Moves.R;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 1 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerScissorsCompPaper() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.S;
        final Moves compMove = Moves.P;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(1 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testOfAddingOnePointToRoundWinnerPlayerScissorsCompScissors() {
        //Given
        gameLogicController.clearCurrentGameRoundPoints(compPlayer, player);
        gameLogicController.createResultLabel();

        final Moves playerMove = Moves.S;
        final Moves compMove = Moves.S;

        //When
        gameLogicController.addOneRoundPointToRoundWinner(player, playerMove, compPlayer, compMove);

        //Then
        Assert.assertTrue(0 == player.getRoundPoints() && 0 == compPlayer.getRoundPoints());
    }

    @Test
    public void testShowCurrentScore() {
        //Given
        player.setRoundPoints(5);
        compPlayer.setRoundPoints(4);

        final String expectedResult = "\nname 5 : 4 Computer\n";

        //When
        final String testedMethodResult = gameLogicController.showCurrentGameScore(player, compPlayer);

        //Then
        Assert.assertTrue(expectedResult.equals(testedMethodResult));
    }

    /* Test of CompPlayer logic */
    @Test
    public void testSetDrawChancesModifier() {
        //Given
        final ByteArrayInputStream stream = new ByteArrayInputStream("10".getBytes());
        final Scanner scanner = new Scanner(stream);

        compPlayer.setDefaultChancesModifiersValues();

        //When
        compPlayer.setDrawChancesModifier(scanner);

        //Then
        Assert.assertTrue(10 == compPlayer.getDrawChancesModifier());
    }

    @Test
    public void testSetWinChancesModifier() {
        //Given
        final ByteArrayInputStream stream = new ByteArrayInputStream("10".getBytes());
        final Scanner scanner = new Scanner(stream);

        compPlayer.setDefaultChancesModifiersValues();

        //When
        compPlayer.setWinChancesModifier(scanner);

        //Then
        Assert.assertTrue(10 == compPlayer.getWinChancesModifier());
    }

    @Test
    public void testSetLoseChancesModifier() {
        //Given
        final ByteArrayInputStream stream = new ByteArrayInputStream("10".getBytes());
        final Scanner scanner = new Scanner(stream);

        compPlayer.setDefaultChancesModifiersValues();

        //When
        compPlayer.setLoseChancesModifier(scanner);

        //Then
        Assert.assertTrue(10 == compPlayer.getLoseChancesModifier());
    }

    @Test
    public void testSetComputerChancesWithPlayerMoveRock() {
        //Given
        final List<Moves> choiceList = new ArrayList<>();
        final Moves playerMove = Moves.R;
        final ByteArrayInputStream loseStream = new ByteArrayInputStream("1".getBytes());
        final ByteArrayInputStream winStream = new ByteArrayInputStream("2".getBytes());
        final ByteArrayInputStream drawStream = new ByteArrayInputStream("3".getBytes());

        compPlayer.setLoseChancesModifier(new Scanner(loseStream));
        compPlayer.setDrawChancesModifier(new Scanner(drawStream));
        compPlayer.setWinChancesModifier(new Scanner(winStream));

        //When
        compPlayer.setComputerChances(playerMove, choiceList);
        long rock = choiceList.stream()
                .filter(moves -> moves.equals(Moves.R))
                .count();
        long paper = choiceList.stream()
                .filter(moves -> moves.equals(Moves.P))
                .count();
        long scissors = choiceList.stream()
                .filter(moves -> moves.equals(Moves.S))
                .count();

        //Then
        Assert.assertTrue(rock == 3 && paper == 2 && scissors == 1);
    }

    @Test
    public void testSetComputerChancesWithPlayerMovePaper() {
        //Given
        final List<Moves> choiceList = new ArrayList<>();
        final Moves playerMove = Moves.P;
        final ByteArrayInputStream loseStream = new ByteArrayInputStream("1".getBytes());
        final ByteArrayInputStream winStream = new ByteArrayInputStream("2".getBytes());
        final ByteArrayInputStream drawStream = new ByteArrayInputStream("3".getBytes());

        compPlayer.setLoseChancesModifier(new Scanner(loseStream));
        compPlayer.setDrawChancesModifier(new Scanner(drawStream));
        compPlayer.setWinChancesModifier(new Scanner(winStream));

        //When
        compPlayer.setComputerChances(playerMove, choiceList);
        long rock = choiceList.stream()
                .filter(moves -> moves.equals(Moves.R))
                .count();
        long paper = choiceList.stream()
                .filter(moves -> moves.equals(Moves.P))
                .count();
        long scissors = choiceList.stream()
                .filter(moves -> moves.equals(Moves.S))
                .count();

        //Then
        Assert.assertTrue(rock == 1 && paper == 3 && scissors == 2);
    }

    @Test
    public void testSetComputerChancesWithPlayerMoveScissors() {
        //Given
        final List<Moves> choiceList = new ArrayList<>();
        final Moves playerMove = Moves.S;
        final ByteArrayInputStream loseStream = new ByteArrayInputStream("1".getBytes());
        final ByteArrayInputStream winStream = new ByteArrayInputStream("2".getBytes());
        final ByteArrayInputStream drawStream = new ByteArrayInputStream("3".getBytes());

        compPlayer.setLoseChancesModifier(new Scanner(loseStream));
        compPlayer.setDrawChancesModifier(new Scanner(drawStream));
        compPlayer.setWinChancesModifier(new Scanner(winStream));

        //When
        compPlayer.setComputerChances(playerMove, choiceList);
        long rock = choiceList.stream()
                .filter(moves -> moves.equals(Moves.R))
                .count();
        long paper = choiceList.stream()
                .filter(moves -> moves.equals(Moves.P))
                .count();
        long scissors = choiceList.stream()
                .filter(moves -> moves.equals(Moves.S))
                .count();

        //Then
        Assert.assertTrue(rock == 2 && paper == 1 && scissors == 3);
    }

    /*Test of UserPlayer class */
    @Test
    public void testUserPlayerMakeMovePaper() {
        //Given
        final Moves expectedMove = Moves.P;
        final ByteArrayInputStream playerMove = new ByteArrayInputStream("p".getBytes());

        //When
        final Moves resultMove = player.makeMove(new Scanner(playerMove));

        //Then
        Assert.assertTrue(expectedMove.equals(resultMove));
    }

    @Test
    public void testUserPlayerMakeMoveScissors() {
        //Given
        final Moves expectedMove = Moves.S;
        final ByteArrayInputStream playerMove = new ByteArrayInputStream("s".getBytes());

        //When
        final Moves resultMove = player.makeMove(new Scanner(playerMove));

        //Then
        Assert.assertTrue(expectedMove.equals(resultMove));
    }

    @Test
    public void testUserPlayerMakeMoveRock() {
        //Given
        final Moves expectedMove = Moves.R;
        final ByteArrayInputStream playerMove = new ByteArrayInputStream("r".getBytes());

        //When
        final Moves resultMove = player.makeMove(new Scanner(playerMove));

        //Then
        Assert.assertTrue(expectedMove.equals(resultMove));
    }

    /* Tests of USerPlayer logic */
    @Test
    public void testAddPlayerToAllPlayersDatabase() {
        //Given
        final UserPlayer somePlayer = new UserPlayer("somePlayer");

        //When
        userController.addPlayerToAllPlayersSet(somePlayer);

        //Then
        Assert.assertTrue(userController.getPlayersSet().contains(somePlayer));
    }

    @Test
    public void testCreateFirstPlayer() {
        //Given
        final String name = "firstPlayer";
        final UserPlayer newPlayer = new UserPlayer(name);
        final ByteArrayInputStream firstPlayerName = new ByteArrayInputStream("firstPlayer".getBytes());

        //When
        userController.createFirstPlayer(new Scanner(firstPlayerName));

        //Then
        Assert.assertEquals(userController.getCurrentPlayer(), newPlayer);
    }

    @Test
    public void testCreateNewPlayer() {
        //Given
        final String name = "randomNewPlayer";
        final UserPlayer newPlayer = new UserPlayer(name);
        final ByteArrayInputStream newPlayerName = new ByteArrayInputStream("randomNewPlayer".getBytes());

        //When
        userController.createNewPlayer(new Scanner(newPlayerName));

        //Then
        Assert.assertEquals(userController.getCurrentPlayer(), newPlayer);
        Assert.assertTrue(userController.getPlayersSet().contains(newPlayer));
    }

    @Test
    public void testChangeCurrentPlayerForExistingPlayer() {
        //Given
        final UserPlayer playerOne = new UserPlayer("player1");
        final UserPlayer playerTwo = new UserPlayer("player2");
        final ByteArrayInputStream playerToChangeName = new ByteArrayInputStream("player1".getBytes());

        userController.addPlayerToAllPlayersSet(playerOne);
        userController.addPlayerToAllPlayersSet(playerTwo);

        //When
        userController.changeCurrentPlayer(new Scanner(playerToChangeName));

        //Then
        Assert.assertEquals(userController.getCurrentPlayer(), playerOne);
    }

    @Test
    public void testChangeCurrentPlayerForNotExistingPlayer() {
        //Given
        final UserPlayer playerOne = new UserPlayer("playerRandom");
        final ByteArrayInputStream playerToChangeName = new ByteArrayInputStream("playerRandom".getBytes());

        //When
        userController.changeCurrentPlayer(new Scanner(playerToChangeName));

        //Then
        Assert.assertFalse(userController.getPlayersSet().contains(playerOne));
        Assert.assertFalse(userController.getCurrentPlayer().equals(playerOne));
    }

    @Test
    public void testIncWonGames() {
        //Given
        final int wonPointsValue = player.getWonGames() + 1;

        //When
        player.incWonGames();

        //Then
        Assert.assertEquals(player.getWonGames(), wonPointsValue);
    }

    @Test
    public void testIncLostGames() {
        //Given
        final int lostPointsValue = player.getLostGames() + 1;

        //When
        player.incLostGames();

        //Then
        Assert.assertEquals(player.getLostGames(), lostPointsValue);
    }
}
