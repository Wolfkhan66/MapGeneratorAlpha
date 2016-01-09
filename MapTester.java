public class MapTester
{

    public static boolean mapTest(int [][] map, int height, int width  ){

        int x = 0;
        int y = 0;
        String output = "";
        
        int [][] routefinder = new int [height][width];
        boolean valid = false;

        boolean bottomright = false;
        boolean topright = false;
        boolean bottomleft = false;

        log("map generated...running tests",1);
        for (x = 0; x <= height-1 ; x++)
        {
            for(y = 0; y <= width-1; y++)
            {
                routefinder[x][y] = 0;
            }
        }

        x = 0;
        y = 0;

        while (valid != true)
        {
            if(map[x][y] == 1 && routefinder[x-1][y] < routefinder[x][y])
            {
                routefinder[x][y] ++;
                x--;
            }
            else if(map[x][y] == 2 && routefinder[x][y-1] < routefinder[x][y])
            {
                routefinder[x][y] ++;
                y--;
            }   
            else if(map[x][y] == 3 && routefinder[x+1][y] < routefinder[x][y])
            {
                routefinder[x][y] ++;
                x++;
            }   
            else if(map[x][y] == 4 && routefinder[x][y+1] < routefinder[x][y])
            {
                routefinder[x][y] ++;
                y++;
            }   
            else if(map[x][y] == 5)
            {
                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                }
                else{
                    routefinder[x][y] ++;
                }
            }
            else if(map[x][y] == 6)
            {
                if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                }
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                }
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 7)
            {
                if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                }
                else if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                }
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 8)
            {
                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                }
                else{
                    routefinder[x][y] ++;
                }
            }   
            else  if(map[x][y] == 9)
            {

                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                } 
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 10)
            {

                if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                }
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                } 
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 11)
            {

                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                } 
                else if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                }
                else{
                    routefinder[x][y] ++;
                }
            }   

            else if(map[x][y] == 12)
            {
                if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                }
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                } 
                else if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                }
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 13)
            {
                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                } 
                else if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                } 
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 14)
            {
                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                } 
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                } 
                else{
                    routefinder[x][y] ++;
                }
            }   
            else if(map[x][y] == 15)
            {
                if (routefinder[x-1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x--;
                }
                else       if (routefinder[x+1][y] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    x++;
                }
                else if (routefinder[x][y-1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y--;
                } 
                else if (routefinder[x][y+1] < routefinder[x][y])
                {
                    routefinder[x][y] ++;
                    y++;
                } 
                else{
                    routefinder[x][y] ++;
                }
            }   

            else{
                routefinder[x][y] ++;
            }

            if (routefinder[x][y] >= (width+height))
            {
                break;
            }

            if( x == height-1 && y == width -1)
            {
                bottomright = true;
            }

            if( x == height-1 && y == 0)
            {
                bottomleft = true;
            }

            if( x == 0 && y == width-1)
            {
                topright = true;
            }
            if(bottomright == true && bottomleft == true && topright == true)
            {   
                valid = true;
                return true;
            }
            log(x + " " + y,1);
            log(" "+routefinder[x][y] + " ",1);

        }
        if (valid == false)
        {
            log("invalid map .... regenerating",1);
            Map.main();
        }

        log("Valid",1);
        return false;
    }

    public static void log(String message, int n)
    {
        if(n == 1)
            System.out.println(message);
        else
            System.out.print(message);
    }
}
