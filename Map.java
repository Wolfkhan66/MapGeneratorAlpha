import java.util.Random;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Map
{
    public static void main()
    {

        int height = 10;
        int width = 10;
        int x = 0;
        int y = 0;
        String output = "";

        int[][]spawnedmap = Mapgen(height,width);

        
        if (MapTester.mapTest(spawnedmap,height,width) == true)
        {
            for (x = 0; x <= height-1 ; x++)
            {
                for(y = 0; y <= width-1; y++)
                {
                    if(spawnedmap[x][y] <= 9)
                    {
                        //System.out.print("0");
                        output += "0";
                    }

                    if(y == width-1)
                    {
                        //System.out.println(map[x][y]);
                        output += spawnedmap[x][y] + " ";
                    }
                    else
                    {
                        //System.out.print(map[x][y] + " ");
                        output += spawnedmap[x][y] + " ";
                    }

                } 
            }
            System.out.println("");

            try(PrintStream out = new PrintStream(new FileOutputStream("map.txt"))){
                out.print(output);
            }
            catch(FileNotFoundException e){
                System.out.println(e);
            }
            System.exit(0);
        }
        else
        {
            main();
        }
       
    }

    public static int[][] Mapgen (int height,int width)
    {
        int[][]map = new int[height][width];   
        Random r = new Random();

        int x = 0;
        int y = 0;
        int a = 0;
        int b = 0;

        int [][] mapcorners ={{3,4,6},   // top left corner
                {7,2,0},   // top right corner
                {5,4,0},   // bottom left corner
                {1,8,0}};   // bottom right corner

        int [][] borderdoors = {{7,2,10,12}, // topborderdoors on the left
                {6,4,10,12}, //  topborderdoors on the right
                {8,9,13,1}, // rightborderdoors on the top
                {3,7,13,9}, // rightborderdoors on the bottom
                {2,8,10,14},   // bottom border doors on the left
                {4,5,10,14},   // bottom border doors on the right
                {1,5,9,11},    // left border doors on the top
                {3,6,9,11}};   // left border doors on the right

        int [][] borderwalls ={{3,7,2},  // topborderwalls on the right
                {6,4,3},   // topborderwalls on the left
                {3,2,7},   // right border walls top 
                {1,2,8},    // right border walls bottom
                {1,4,5},    //bottom border walls left
                {1,2,8},    //bottomer border walls right
                {3,4,6},    // left border walls top
                {1,4,5}};   // left border walls bottom

        int [][] lastrow = {{13,15},   // Door left , Door above, Door below
                {14,8},     //Door left, Door above, Wall below
                {12,7},     //Door left, Wall above, Door below
                {10,2},     //Door left, Wall above, Wall below
                {4, 0},     //Wall left, Wall above, Wall below
                {6, 3},     //Wall left, Wall above, Door below
                {5,1},      //Wall left, Door above, Wall below
                {9,11}};    //Wall left, Door above, Door below

        int[][] roomdoors = {{3,6,7,9,11,12,13,15}, // doors on the bottom
                {1,5,8,9,11,13,14,15},             // doors on the top
                { 2,7,8,10,12,13,14,15,},          // doors on the left
                {4,5,6,10,11,12,14,15}};            // doors on the right

        int[][] roomwalls = {{1,2,4,5,8,10,14}, // walls bottom
                {2,3,4,6,7,10,12},  // walls top
                {1,2,3,7,8,9,13},   // walls right
                {1,3,4,5,6,9,11}};   // walls left

        int [][] threeroomcheck = {{3,0},   //wall above, wall left , wall right
                {7,2},  //wall above, door left, wall right
                {14,15},//door above, door left, door right
                {8,13}, //door above, door left, wall right
                {5,11}, //door above, wall left, door right
                {9,1},  //door above, wall left, wall right
                {12,10},//wall above, door left, door right
                {6,4}}; //wall above, wall left, door right

        int [][] tworoomcheck ={{8,13,14,15},   //door above, door left
                {5,9,11,0},    //door above, wall left
                {6,3,4,0},     //wall above, wall left
                {2,7,10,12},  //wall above, door left
                {5,1,0,0},      //door above , wall below
                {9,11,0,0},    // door above, door below
                {6,3,0,0}};    // wall above, door below

        map[0][0] = 4;

        String output = "";

        // top border
        for (x = 0; x <= width - 1; x++)
        {
            if (map[0][x] == 0)
            {   
                for ( y = 0; y <= 3; y++)
                {
                    if(map[0][x-1] == borderdoors[1][y])
                    {
                        map[0][x] = borderdoors[0][r.nextInt(4 - 1 + 1) + 1 -1];
                    }

                }
                for ( y = 0; y <= 2; y++)
                {
                    if(map[0][x-1] == borderwalls[0][y])
                    {
                        map[0][x] = borderwalls[1][r.nextInt(3 - 1 + 1) + 1 -1];
                    }

                }
                if ( x == width-1){
                    for ( y = 0; y <= 2; y++)
                    {
                        if(map[0][x-1] == borderwalls[0][y])
                        {
                            map[0][x] = 3;
                        }
                    }
                }
                if ( x == width-1){
                    for(a =0; a <=3; a++){
                        if(map[0][x-1] == borderdoors[1][a])
                        {
                            map[0][x] = mapcorners[1][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }

                }
            }
        }

        //right border
        for (x = 0; x <= height - 1; x++)
        {
            if (map[x][width-1] == 0)
            {   
                for ( y = 0; y <= 3; y++)
                {
                    if(map[x-1][width-1] == borderdoors[3][y])
                    {
                        map[x][width-1] = borderdoors[2][r.nextInt(4 - 1 + 1) + 1 -1];
                    }

                }
                for ( y = 0; y <= 2; y++)
                {
                    if(map[x-1][width-1] == borderwalls[3][y])
                    {
                        map[x][width-1] = borderwalls[2][r.nextInt(3 - 1 + 1) + 1 -1];
                    }

                }
                if ( x == height-1){
                    for ( y = 0; y <= 2; y++)
                    {

                        if(map[x-1][width-1] == borderwalls[3][y])
                        {
                            map[x][width-1] = 2;
                        }
                    }
                    if ( x == height-1){
                        for(a =0; a <=3; a++){

                            if(map[x-1][width-1] == borderdoors[3][a])
                            {
                                map[x][width-1] = mapcorners[3][r.nextInt(2 - 1 + 1) + 1 -1]; 
                            }
                        }
                    }

                }
            }
        }

        //bottom border
        for (x = width-1; x >= 0; x--)
        {
            if (map[height-1][x] == 0)
            {   
                for ( y = 0; y <= 3; y++)
                {
                    if(map[height-1][x+1] == borderdoors[4][y])
                    {
                        map[height-1][x] = borderdoors[5][r.nextInt(4 - 1 + 1) + 1 -1];
                    }

                }
                for ( y = 0; y <= 2; y++)
                {
                    if(map[height-1][x+1] == borderwalls[4][y])
                    {
                        map[height-1][x] = borderwalls[5][r.nextInt(3 - 1 + 1) + 1 -1];
                    }

                }
                if ( x == 0){
                    for ( y = 0; y <= 2; y++)
                    {

                        if(map[height-1][x+1] == borderwalls[4][y])
                        {
                            map[height-1][x] = 1;
                        }
                    }
                    if ( x == 0){
                        for(a =0; a <=3; a++){
                            if(map[height-1][x+1] == borderdoors[4][a])
                            {
                                map[height-1][x] = mapcorners[2][r.nextInt(2 - 1 + 1) + 1 -1];
                            }
                        }
                    }
                }}
        }

        //left border
        for (x = height-1; x >= 0; x--)
        {
            if (map[x][0] == 0)
            {   
                for ( y = 0; y <= 3; y++)
                {
                    if(map[x+1][0] == borderdoors[6][y])
                    {
                        map[x][0] = borderdoors[7][r.nextInt(4 - 1 + 1) + 1 -1];
                    }

                }
                for ( y = 0; y <= 2; y++)
                {
                    if(map[x+1][0] == borderwalls[6][y])
                    {
                        map[x][0] = borderwalls[7][r.nextInt(3 - 1 + 1) + 1 -1];
                    }

                }

                if ( x == 1){
                    for(a = 0; a <= 6; a++)
                    {
                        for(b = 0; b <=6; b++)
                        {

                            //wall above, wall below
                            if(map[0][0] == roomwalls[0][a] && map[2][0] ==  roomwalls[1][b] )
                            {
                                map[x][0] = 4;
                            }
                        }
                    }
                    for(a = 0; a <= 7; a++)
                    {
                        for(b = 0; b <=6; b++)
                        {
                            //door above , wall below
                            if(map[0][0] == roomdoors[0][a] && map[2][0] == roomwalls[1][b])
                            {
                                map[x][0] = tworoomcheck[4][r.nextInt(2 - 1 + 1) + 1 -1];
                            }
                        }
                    }
                    for(a = 0; a <= 7; a++)
                    {
                        for(b = 0; b <=7; b++)
                        {
                            // door above, door below
                            if(map[0][0] == roomdoors[0][a] && map[2][0] == roomdoors[1][b])
                            {
                                map[x][0] = tworoomcheck[5][r.nextInt(2 - 1 + 1) + 1 -1];
                            }  
                        }
                    }
                    for(a= 0; a <=6;a++)
                    {
                        for(b = 0; b <=7; b++)
                        {
                            // wall above, door below
                            if(map[0][0] == roomwalls[0][a] && map[2][0] == roomdoors[1][b])
                            {
                                map[x][0] = tworoomcheck[6][r.nextInt(2 - 1 + 1) + 1 -1];
                            }  
                        }
                    }
                }
            }
        }

        // fill in the center except the 2nd to last column and row
        for(x = 1; x <= height-2 ; x++)
        {
            for( y = 1; y <= width-2; y++)
            {
                if (map[x][y] == 0)
                {   
                    for(a = 0; a <= 7; a++)
                    {
                        for(b = 0; b <= 7; b++)
                        {
                            if(map[x-1][y] == roomdoors[0][a] && map[x][y-1] == roomdoors[3][b])
                            {   
                                map[x][y] = tworoomcheck[0][r.nextInt(4 - 1 + 1) + 1 -1];

                            }
                        }
                    }

                    for(a = 0; a <= 7; a++)
                    {
                        for(b = 0; b <= 6; b++)
                        {
                            if(map[x-1][y] == roomdoors[0][a] && map[x][y-1] == roomwalls[2][b])
                            {   
                                map[x][y] = tworoomcheck[1][r.nextInt(3 - 1 + 1) + 1 -1];

                            }
                        }
                    }

                    for(a = 0; a <= 6; a++)
                    {
                        for(b = 0; b <= 6; b++)
                        {
                            if(map[x-1][y] == roomwalls[0][a] && map[x][y-1] == roomwalls[2][b])
                            {   
                                map[x][y] = tworoomcheck[2][r.nextInt(3 - 1 + 1) + 1 -1];

                            }
                        }
                    }

                    for(a = 0; a <= 6; a++)
                    {
                        for(b = 0; b <= 7; b++)
                        {
                            if(map[x-1][y] == roomwalls[0][a] && map[x][y-1] == roomdoors[3][b])
                            {   
                                map[x][y] = tworoomcheck[3][r.nextInt(4 - 1 + 1) + 1 -1];

                            }
                        }
                    }
                }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /// fill in the 2nd to last column 
        for(x = 1; x <= width - 2; x++)
        {

            //wall above, wall left , wall right
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[x-1][width-2] == roomwalls[0][a] && map[x][width-3] == roomwalls[2][b] && map[x][width-1] == roomwalls[3][y])
                        {   
                            map[x][width-2] = threeroomcheck[0][r.nextInt(1 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //wall above, door left, wall right
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[x-1][width-2] == roomwalls[0][a] && map[x][width-3] == roomdoors[3][b] && map[x][width-1] == roomwalls[3][y])
                        {   
                            map[x][width-2] = threeroomcheck[1][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //door above, door left, door right
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[x-1][width-2] == roomdoors[0][a] && map[x][width-3] == roomdoors[3][b] && map[x][width-1] == roomdoors[2][y])
                        {   
                            map[x][width-2] = threeroomcheck[2][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //door above, door left, wall right
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[x-1][width-2] == roomdoors[0][a] && map[x][width-3] == roomdoors[3][b] && map[x][width-1] == roomwalls[3][y])
                        {   
                            map[x][width-2] = threeroomcheck[3][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //door above, wall left, door right
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[x-1][width-2] == roomdoors[0][a] && map[x][width-3] == roomwalls[2][b] && map[x][width-1] == roomdoors[2][y])
                        {   
                            map[x][width-2] = threeroomcheck[4][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //door above, wall left, wall right
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[x-1][width-2] == roomdoors[0][a] && map[x][width-3] == roomwalls[2][b] && map[x][width-1] == roomwalls[3][y])
                        {   
                            map[x][width-2] = threeroomcheck[5][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //wall above, door left, door right
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[x-1][width-2] == roomwalls[0][a] && map[x][width-3] == roomdoors[3][b] && map[x][width-1] == roomdoors[2][y])
                        {   
                            map[x][width-2] = threeroomcheck[6][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

            //wall above, wall left, door right
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[x-1][width-2] == roomwalls[0][a] && map[x][width-3] == roomwalls[2][b] && map[x][width-1] == roomdoors[2][y])
                        {   
                            map[x][width-2] = threeroomcheck[7][r.nextInt(2 - 1 + 1) + 1 -1];

                        }
                    }
                }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // fill in the 2nd to last row
        for(x = 1; x <= width-2; x++)
        {

            // Door left , Door above, Door below
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[height-2][x-1] == roomdoors[3][a] && map[height-3][x] == roomdoors[0][b] && map[height-1][x] == roomdoors[1][y])
                        {   
                            map[height-2][x] = lastrow[0][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

            //Door left, Door above, Wall below
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[height-2][x-1] == roomdoors[3][a] && map[height-3][x] == roomdoors[0][b] && map[height-1][x] == roomwalls[1][y])
                        {   
                            map[height-2][x] = lastrow[1][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

            //Door left, Wall above, Door below
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[height-2][x-1] == roomdoors[3][a] && map[height-3][x] == roomwalls[0][b] && map[height-1][x] == roomdoors[1][y])
                        {   
                            map[height-2][x] = lastrow[2][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

            //Door left, Wall above, Wall below
            for(a = 0; a <= 7; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[height-2][x-1] == roomdoors[3][a] && map[height-3][x] == roomwalls[0][b] && map[height-1][x] == roomwalls[1][y])
                        {   
                            map[height-2][x] = lastrow[3][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

            //Wall left, Wall above, Wall below
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[height-2][x-1] == roomwalls[2][a] && map[height-3][x] == roomwalls[0][b] && map[height-1][x] == roomwalls[1][y])
                        {   
                            map[height-2][x] = 4;
                        }
                    }
                }
            }

            //Wall left, Wall above, Door below
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 6; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[height-2][x-1] == roomwalls[2][a] && map[height-3][x] == roomwalls[0][b] && map[height-1][x] == roomdoors[1][y])
                        {   
                            map[height-2][x] = lastrow[5][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

            //Wall left, Door above, Wall below
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 6; y++)
                    {
                        if(map[height-2][x-1] == roomwalls[2][a] && map[height-3][x] == roomdoors[0][b] && map[height-1][x] == roomwalls[1][y])
                        {   
                            map[height-2][x] = lastrow[6][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

            //Wall left, Door above, Door below
            for(a = 0; a <= 6; a++)
            {
                for(b = 0; b <= 7; b++)
                {
                    for(y = 0; y <= 7; y++)
                    {
                        if(map[height-2][x-1] == roomwalls[2][a] && map[height-3][x] == roomdoors[0][b] && map[height-1][x] == roomdoors[1][y])
                        {   
                            map[height-2][x] = lastrow[7][r.nextInt(2 - 1 + 1) + 1 -1];
                        }
                    }
                }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // final cell check
        // Door above, wall below, wall left, wall right = 1
        for (x = 0; x < 7; x++)
        {

            for(y = 0; y < 6; y++)
            {

                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 1;
                        }
                    }
                }
            }            
        }

        // Wall above, wall below, door left, wall right = 2
        for (x = 0; x < 6; x++)
        {

            for(y = 0; y < 6; y++)
            {

                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 2;
                        }
                    }
                }
            }            
        }

        // wall above, door below, wall left, wall right = 3
        for (x = 0; x < 6; x++)
        {

            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 3;
                        }
                    }
                }
            }            
        }

        // wall above, wall below, wall left, door right = 4
        for (x = 0; x < 6; x++)
        {
            for(y = 0; y < 6; y++)
            {
                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 4;
                        }
                    }
                }
            }            
        }

        // door above, wall below, wall left, door right = 5
        for (x = 0; x < 7; x++)
        {
            for(y = 0; y < 6; y++)
            {
                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 5;
                        }
                    }
                }
            }            
        }

        // wall above, door below, wall left, door right = 6
        for (x = 0; x < 6; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 6;
                        }
                    }
                }
            }            
        }

        // wall above, door below, door left, wall right = 7
        for (x = 0; x < 6; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 7;
                        }
                    }
                }
            }            
        }

        // door above, wall below, door left, wall right = 8
        for (x = 0; x <7; x++)
        {
            for(y = 0; y < 6; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 8;
                        }
                    }
                }
            }            
        }

        // door above, door below, wall left, wall right = 9
        for (x = 0; x <7; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 9;
                        }
                    }
                }
            }            
        }

        // wall above, wall below, door left, door right  = 10
        for (x = 0; x < 6; x++)
        {
            for(y = 0; y < 6; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 10;
                        }
                    }
                }
            }            
        }

        // door above, door below, wall left, door right = 11
        for (x = 0; x < 7; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 6; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomwalls[2][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 11;
                        }
                    }
                }
            }            
        }

        // wall above, door below, door left, door right = 12
        for (x = 0; x < 6; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomwalls[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 12;
                        }
                    }
                }
            }            
        }

        // door above, door below, door left, wall right = 13
        for (x = 0; x < 7; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 6; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomwalls[3][b])
                        {
                            map[height-2][width-2] = 13;
                        }
                    }
                }
            }            
        }

        // door above, wall below, door left, door right = 14
        for (x = 0; x < 7; x++)
        {
            for(y = 0; y < 6; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomwalls[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 14;
                        }
                    }
                }
            }            
        }

        // door above, door below, door left, door right = 15
        for (x = 0; x < 7; x++)
        {
            for(y = 0; y < 7; y++)
            {
                for(a = 0; a < 7; a++)
                {
                    for(b = 0; b < 7; b++)
                    {
                        if (map[height-3][width-2] == roomdoors[0][x] && map[height-1][width-2] == roomdoors[1][y] && map[height-2][width-3] == roomdoors[3][a] && map[height-2][width-1] == roomdoors[2][b])
                        {
                            map[height-2][width-2] = 15;
                        }
                    }
                }
            }            
        }

        // print out the map
        for (x = 0; x <= height-1 ; x++)
        {
            for(y = 0; y <= width-1; y++)
            {
                if(map[x][y] <= 9)
                {
                    System.out.print("0");
                    //  output += "0";
                }

                if(y == width-1)
                {
                    System.out.println(map[x][y]);
                    // output += map[x][y] + " ";
                }
                else
                {
                    System.out.print(map[x][y] + " ");
                    //  output += map[x][y] + " ";
                }

            } 
        }
        System.out.println("");
        return map;
    }

}