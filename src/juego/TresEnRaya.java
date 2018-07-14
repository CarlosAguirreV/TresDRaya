package juego;

/**
 * Clase principal del juego tres en raya y tres en raya en tres dimensiones.
 * @version 28-05-2018
 * @author Carlos Aguirre Vozmediano
 * Gráficos: Java Swing.
 * Juego: Tres en raya y tres en raya tres dimensiones.
 */
public class TresEnRaya
{
    private boolean juegoNormal;
    private boolean jugador;
    private boolean finPartida;
    private final byte LONGITUD = 3;
    private byte[][][] tablero;
    private byte turno;
    private byte ganador;
    private byte corGanadora;
    private byte tableroGanador;
    
    /**
     * Establece todos los valores a un estado correcto.
     * @param pJuegoNormal Especifica si se trata de un tres en raya normal (true) o tres en raya en tres dimensiones (false).
     */
    public TresEnRaya(boolean pJuegoNormal)
    {
        this.juegoNormal = pJuegoNormal;
        if(this.juegoNormal){this.tablero = new byte[LONGITUD][LONGITUD][1];}
        else{this.tablero = new byte[LONGITUD][LONGITUD][LONGITUD];}
        this.jugador = true;
        this.resetearTablero();
    }
    
    /**
     * Controla si se puede poner una ficha en una determinada coordenada, la coloca si puede y comprueba si hay un ganador.
     * @param x Número entero (byte) que define la coordenada X.
     * @param y Número entero (byte) que define la coordenada Y.
     * @return true Si se ha podido colocar la ficha y false en caso contrario.
     */
    public boolean control(byte x, byte y)
    {
        if(!this.finPartida && verificarCoordenadas(x,y))
        {
            if(this.setFicha(x, y))
            {
                this.turno++;
                this.jugador = !this.jugador;
                this.comprobarTablero();
                return true;
            }
            comprobarTablero();
        }
        return false;
    }
    
    /**
     * Controla si se puede poner una ficha en una determinada coordenada, la coloca si puede y comprueba si hay un ganador.
     * @param x Número entero (byte) que define la coordenada X.
     * @param y Número entero (byte) que define la coordenada Y.
     * @param z Número enero (byte) que define la coordenada Z.
     * @return true Si se ha podido colocar la ficha y false en caso contrario.
     */
    public boolean control(byte x, byte y, byte z)
    {
        if(!this.finPartida && verificarCoordenadas(x,y,z))
        {
            if(this.setFicha(x, y, z))
            {
                this.turno++;
                this.jugador = !this.jugador;
                comprobarTablero();
                return true;
            }
            comprobarTablero();
        }
        return false;
    }
    
    /**
     * Este método añade una ficha al tablero si está vacia, devuelve True si se ha podido poner la ficha y False si ya estaba ocupado esa posición.
     * @param x Define la coordenada x - Las filas.
     * @param y Define la coordenada y - Las columnas.
     * @return boolean True si puede poner la ficha, False en caso contrario.
     */
    private boolean setFicha(byte x, byte y)
    {
        if(tablero[x][y][0] == 0){tablero[x][y][0] = this.jugador ? (byte)-1 : (byte)1; return true;}
        else{return false;}
    }
    
    /**
     * Este método añade una ficha al tablero si está vacia, devuelve True si se ha podido poner la ficha y False si ya estaba ocupado esa posición.
     * @param x Define la coordenada x - Las filas.
     * @param y Define la coordenada y - Las columnas.
     * @param z Define la coordenada z - El tablero.
     * @return boolean True si puede poner la ficha, False en caso contrario.
     */
    private boolean setFicha(byte x, byte y, byte z)
    {
        if(tablero[x][y][z] == 0){tablero[x][y][z] = this.jugador ? (byte)-1 : (byte)1; return true;}
        else{return false;}
    }
    
    /**
     * Define el campo 'ganador' que es quien ha ganado la partida.
     * '0' - Empate.      '-1' - Gana J1        '2' - Gana J2
     * @param valor Tipo (byte) admite solo dos valores -3 ó 3.
     */
    private void setGanador(byte valor){this.ganador = (valor == -3 ? (byte)-1 : (byte)1);}
    
    /**
     * Comprueba filas y columnas al mismo tiempo para determinar si hay un ganador.
     * Si hay ganador se define el atributo 'finPartida' a True, se define el ganador y se almacena la coordenada que hizo posible ganar.
     */
    private void comprobarTablero()
    {
        if(this.turno > 4)
        {
            boolean detectadoGanador = false;
            
            // Comprobación para el juego tres en raya normal.
            if(this.juegoNormal)
            {
                for(byte x = 0; x < this.LONGITUD; x++)
                {
                    byte filas = 0;
                    byte columnas = 0;
                    for(byte y = 0; y < this.LONGITUD; y++)
                    {
                        if(this.tablero[x][y][0] != 0){filas += this.tablero[x][y][0];}
                        if(this.tablero[y][x][0] != 0){columnas += this.tablero[y][x][0];}
                    }
                    if(filas == -3 || filas == +3)
                    {
                        this.auxiliarComprobador(filas);
                        this.corGanadora = (byte)(x + 1);
                        detectadoGanador = true;
                        break;
                    }
                    else if(columnas == -3 || columnas == +3)
                    {
                        this.auxiliarComprobador(columnas);
                        this.corGanadora = (byte)((x + 1) * -1);
                        detectadoGanador = true;
                        break;
                    }
                }

                if(!detectadoGanador)
                {
                    // Comprobar diagonal 1:
                    byte a = this.tablero[2][0][0];
                    a += this.tablero[1][1][0];
                    a += this.tablero[0][2][0];
                    if(a == -3 || a == +3){this.auxiliarComprobador(a); this.corGanadora = 4;}

                    // Comprobar diagonal 2:
                    byte b = this.tablero[0][0][0];
                    b += this.tablero[1][1][0];
                    b += this.tablero[2][2][0];
                    if(b == -3 || b == +3){this.auxiliarComprobador(b); this.corGanadora = 5;}
                }

                if(!detectadoGanador && this.turno >= 9){this.finPartida = true;}
            }
            // Comprobacion para el juego tres en raya en tres dimensiones.
            else
            {
                // Esta primera comprobación va a comprobar cada tablero individual.
                for(int z = 0; z < this.LONGITUD; z++)
                {
                    for(byte x = 0; x < this.LONGITUD; x++)
                    {
                        byte filas = 0;
                        byte columnas = 0;
                        for(byte y = 0; y < this.LONGITUD; y++)
                        {
                            if(this.tablero[x][y][z] != 0){filas += this.tablero[x][y][z];}
                            if(this.tablero[y][x][z] != 0){columnas += this.tablero[y][x][z];}
                        }
                        if(filas == -3 || filas == +3)
                        {
                            this.auxiliarComprobador(filas, (byte)z);
                            this.corGanadora = (byte)(x + 1);
                            detectadoGanador = true;
                            break;
                        }
                        else if(columnas == -3 || columnas == +3)
                        {
                            this.auxiliarComprobador(columnas, (byte)z);
                            this.corGanadora = (byte)((x + 1) * -1);
                            detectadoGanador = true;
                            break;
                        }
                    }
                }
                // Esta segunda comprobación va a comprobar las diagonales de cada tablero individual.
                if(!detectadoGanador)
                {
                    for(int z = 0; z < this.LONGITUD; z++)
                    {
                        // Comprobar diagonal 1:
                        byte a = this.tablero[2][0][z];
                        a += this.tablero[1][1][z];
                        a += this.tablero[0][2][z];
                        if(a == -3 || a == +3)
                        {
                            this.auxiliarComprobador(a, (byte)z);
                            this.corGanadora = 4;
                            detectadoGanador = true;
                            break;
                        }

                        // Comprobar diagonal 2:
                        byte b = this.tablero[0][0][z];
                        b += this.tablero[1][1][z];
                        b += this.tablero[2][2][z];
                        if(b == -3 || b == +3)
                        {
                            this.auxiliarComprobador(b, (byte)z);
                            this.corGanadora = 5;
                            detectadoGanador = true;
                            break;
                        }
                    }
                }

                // Esta comprobación va a comprobar los tres tableros a la vez por columnas.
                if(!detectadoGanador)
                {
                    for(byte x = 0; x < this.LONGITUD; x++)
                    {
                        for(byte y = 0; y < this.LONGITUD; y++)
                        {
                            byte suma = 0;
                            for(byte z = 0; z < this.LONGITUD; z++)
                            {
                                if(this.tablero[x][y][z] != 0){suma += this.tablero[x][y][z];}
                                if(suma == 3 || suma == -3)
                                {
                                    this.auxiliarComprobador(suma, (byte)3);
                                    switch (y) {
                                        case 0: this.corGanadora = (byte)(6 + x + y); break;
                                        case 1: this.corGanadora = (byte)(6 + (x+1) + (y*2)); break;
                                        case 2: this.corGanadora = (byte)(6 + (x+2) + (y*2)); break;
                                        default: break;
                                    }
                                    detectadoGanador = true;
                                    break;
                                }
                            }
                        }
                    }
                }

                // Comprobación de todas las diagonales exceptuando las diagonales verticales y horizontales de las esquinas.
                if(!detectadoGanador)
                {
                    for(byte x = 0; x < this.LONGITUD; x++)
                    {
                        for(byte y = 0; y < this.LONGITUD; y++)
                        {
                            byte diagonalFija = 0;
                            if(x == 1 && y == 1){continue;} // Para que se salte la columna del centro que ya la ha comprobado anteriormente.
                            if(this.tablero[x][y][0] != 0){diagonalFija += this.tablero[x][y][0];}
                            if(this.tablero[1][1][1] != 0){diagonalFija += this.tablero[1][1][1];} // Centro, es fijo.
                            byte xContrario = x;
                            byte yContrario = y;
                            if(x == 0){xContrario = 2;}
                            if(y == 0){yContrario = 2;}
                            if(x == 2){xContrario = 0;}
                            if(y == 2){yContrario = 0;}
                            if(this.tablero[xContrario][yContrario][2] != 0){diagonalFija += this.tablero[xContrario][yContrario][2];}

                            if(diagonalFija == 3 || diagonalFija == -3)
                            {
                                if (x == 0 && y == 0){this.corGanadora = 15;}
                                else if(x == 1 && y == 0){this.corGanadora = 16;}
                                else if(x == 2 && y == 0){this.corGanadora = 17;}
                                else if(x == 0 && y == 1){this.corGanadora = 22;}
                                else if(x == 2 && y == 1){this.corGanadora = 18;}
                                else if(x == 0 && y == 2){this.corGanadora = 21;}
                                else if(x == 1 && y == 2){this.corGanadora = 20;}
                                else if(x == 2 && y == 2){this.corGanadora = 19;}
                                this.auxiliarComprobador(diagonalFija, (byte)3);
                                detectadoGanador = true;
                                break;
                            }
                        }
                        if(this.finPartida){break;}
                    }
                }

                // Comprobación de las diagonales horizontales y verticales.
                // ¿Se puede mejorar?
                if(!detectadoGanador)
                {
                    byte diagonalHoriVer = 0;
                    if(this.tablero[0][0][0] != 0){diagonalHoriVer += this.tablero[0][0][0];}
                    if(this.tablero[1][0][1] != 0){diagonalHoriVer += this.tablero[1][0][1];}
                    if(this.tablero[2][0][2] != 0){diagonalHoriVer += this.tablero[2][0][2];}
                    if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                    {
                        this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                        this.corGanadora = 23;
                        detectadoGanador = true;
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[0][0][0] != 0){diagonalHoriVer += this.tablero[0][0][0];}
                        if(this.tablero[0][1][1] != 0){diagonalHoriVer += this.tablero[0][1][1];}
                        if(this.tablero[0][2][2] != 0){diagonalHoriVer += this.tablero[0][2][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 24;
                            detectadoGanador = true;
                        }
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[2][0][0] != 0){diagonalHoriVer += this.tablero[2][0][0];}
                        if(this.tablero[2][1][1] != 0){diagonalHoriVer += this.tablero[2][1][1];}
                        if(this.tablero[2][2][2] != 0){diagonalHoriVer += this.tablero[2][2][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 25;
                            detectadoGanador = true;
                        }
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[2][0][0] != 0){diagonalHoriVer += this.tablero[2][0][0];}
                        if(this.tablero[1][0][1] != 0){diagonalHoriVer += this.tablero[1][0][1];}
                        if(this.tablero[0][0][2] != 0){diagonalHoriVer += this.tablero[0][0][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 26;
                            detectadoGanador = true;
                        }
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[2][2][0] != 0){diagonalHoriVer += this.tablero[2][2][0];}
                        if(this.tablero[1][2][1] != 0){diagonalHoriVer += this.tablero[1][2][1];}
                        if(this.tablero[0][2][2] != 0){diagonalHoriVer += this.tablero[0][2][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 27;
                            detectadoGanador = true;
                        }
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[2][2][0] != 0){diagonalHoriVer += this.tablero[2][2][0];}
                        if(this.tablero[2][1][1] != 0){diagonalHoriVer += this.tablero[2][1][1];}
                        if(this.tablero[2][0][2] != 0){diagonalHoriVer += this.tablero[2][0][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 28;
                            detectadoGanador = true;
                        }
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[0][2][0] != 0){diagonalHoriVer += this.tablero[0][2][0];}
                        if(this.tablero[0][1][1] != 0){diagonalHoriVer += this.tablero[0][1][1];}
                        if(this.tablero[0][0][2] != 0){diagonalHoriVer += this.tablero[0][0][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 29;
                            detectadoGanador = true;
                        }
                    }

                    if(!detectadoGanador)
                    {
                        diagonalHoriVer = 0;
                        if(this.tablero[0][2][0] != 0){diagonalHoriVer += this.tablero[0][2][0];}
                        if(this.tablero[1][2][1] != 0){diagonalHoriVer += this.tablero[1][2][1];}
                        if(this.tablero[2][2][2] != 0){diagonalHoriVer += this.tablero[2][2][2];}
                        if(diagonalHoriVer == 3 || diagonalHoriVer == -3)
                        {
                            this.auxiliarComprobador(diagonalHoriVer, (byte)3);
                            this.corGanadora = 30;
                            detectadoGanador = true;
                        }
                    }
                }

                if(!detectadoGanador && this.turno >= 27){this.finPartida = true;}
            }
        }
    }
    
    /**
     * Ayuda a quitar la redundancia de código del método comprobarTablero() del tres en raya normal.
     * @param ganador Jugador que ha ganado la partida (-1 (J1), 0 (Empate), 1 (J2)).
     */
    private void auxiliarComprobador(byte ganador)
    {
        this.setGanador(ganador);
        this.finPartida = true;
    }
    
    /**
     * Ayuda a quitar la redundancia de código del método comprobarTablero() del tres en raya en tres dimensiones.
     * @param ganador Jugador que ha ganado la partida (-1 (J1), 0 (Empate), 1 (J2)). 
     * @param tabGanador Tablero que tiene la jugada ganadora: -1 (nadie gana, empate), 0 (1º Tablero), 1 (2º Tablero), 2 (3º Tablero), 3 (Combinación de los tres).
     */
    private void auxiliarComprobador(byte ganador,byte tabGanador)
    {
        this.setGanador(ganador);
        this.tableroGanador = tabGanador;
        this.finPartida = true;
    }
    
    /**
     * Establece todos los atributos y las variables del tablero (Array tridimensional) a 0.
     */
    public void resetearTablero()
    {
        this.ganador = 0;
        this.turno = 0;
        this.finPartida = false;
        this.corGanadora = 0;
        this.tableroGanador = -1;
        
        if(this.juegoNormal)
        {
            for(byte x = 0; x < this.LONGITUD; x++)
            {
                for(byte y = 0; y < this.LONGITUD; y++)
                {
                    this.tablero[x][y][0] = 0;
                }
            }
        }
        else
        {
            for(byte x = 0; x < this.LONGITUD; x++)
            {
                for(byte y = 0; y < this.LONGITUD; y++)
                {
                    for(byte z = 0; z < this.LONGITUD; z++)
                    {
                        this.tablero[x][y][z] = 0;
                    }   
                }
            }
        }
    }
    
    /**
     * Devuelve el tablero que ha ganado la partida.
     * Tablero ganador puede ser: -1 (nadie gana, empate), 0 (1º Tablero), 1 (2º Tablero), 2 (3º Tablero), 3 (Combinación de los tres)
     * @return byte
     */
    public byte getTableroGanador(){return this.tableroGanador;}
    
    /**
     * Devuelve True si se ha acabado la partida o False si no.
     * @return boolean
     */
    public boolean isFinPartida(){return this.finPartida;}
    
    /**
     * Obtiene un valor byte que define al ganador. (-1 J1),(1 J2),(0 Empate)
     * @return byte (-1, 0, 1)
     */
    public byte getGanador(){return this.ganador;}
    
    /**
     * Devuelve true si le toca al J1 y false si le toca al J2.
     * @return boolean
     */
    public boolean getJugador(){return this.jugador;}
    
    /**
     * Obtiene el número de turno.
     * @return byte
     */
    public byte getTurno(){return this.turno;}
    
    /**
     * Devuelve un valor de tipo byte que define la coordenada que ha ganado.
     * @return (-3 - 5)
     * Coordenada ganadora: x(-1,-2,-3),y(1,2,3), abajo-izquierda a arriba-derecha(4), abajo-derecha a arriba izquierda(5)
     */
    public byte getCoordenadaGanadora(){return this.corGanadora;}
    
    /**
     * Obtiene el tamaño del tablero (3);
     * @return byte (3);
     */
    public byte getTamanioTablero(){return this.LONGITUD;}
    
    /**
     * Obtiene el valor de la casilla especificada.
     * @param x Define la coordenada x - Las filas.
     * @param y Define la coordenada y - Las columnas.
     * @return -1, 0, 1 (0 Vacio, 1 J1, -1 J2)
     */
    public byte getValorCasilla(byte x, byte y)
    {
        if(this.verificarCoordenadas(x,y)){return this.tablero[x][y][0];}
        else{return (byte)-2;}
    }
    
    /**
     * Obtiene el valor de la casilla especificada.
     * @param x Define la coordenada x - Las filas.
     * @param y Define la coordenada y - Las columnas.
     * @param z Define la coordenada z - El tablero.
     * @return -1, 0, 1 (0 Vacio, 1 J1, -1 J2)
     */
    public byte getValorCasilla(byte x, byte y, byte z)
    {
        if(this.verificarCoordenadas(x,y,z)){return this.tablero[x][y][z];}
        else{return (byte)-2;}
    }
    
    /**
     * Comprueba si las coordenadas introducidas son correctas.
     * @param x Define la coordenada x - Las filas.
     * @param y Define la coordenada y - Las columnas.
     * @return boolean
     */
    public boolean verificarCoordenadas(byte x, byte y)
    {
        if(x >= 0 || x < this.LONGITUD && y >= 0 || y < this.LONGITUD){return true;}
        else{return false;}
    }
    
    /**
     * Comprueba si las coordenadas introducidas son correctas.
     * @param x Define la coordenada x - Las filas.
     * @param y Define la coordenada y - Las columnas.
     * @param z Define la coordenada z - El tablero.
     * @return boolean
     */
    public boolean verificarCoordenadas(byte x, byte y, byte z)
    {
        if(x >= 0 || x < this.LONGITUD && y >= 0 || y < this.LONGITUD && z >= 0 || z < this.LONGITUD){return true;}
        else{return false;}
    }
    
    /**
     * Muestra el tablero por consola de texto, este método es solo para comprobaciones que realiza el desarrollador.
     */
    public void mostrarTablero()
    {
        System.out.println("\nTurno: " + Byte.toString(this.getTurno()) + " / Jugador: " + (this.jugador ? "O" : "X"));
        System.out.println(this.finPartida ? "OK Gana: " + Byte.toString(this.ganador) : "NO HA GANADO NADIE AÚN");
        if(this.juegoNormal)
        {
            for(byte y = 0; y < this.LONGITUD; y++)
            {
                for(byte x = 0; x < this.LONGITUD; x++)
                {
                    System.out.printf("%-3s", this.tablero[x][y]);
                }
                System.out.println("");
            }
        }
        else
        {
            for(byte z = 0; z < this.LONGITUD; z++)
            {
                System.out.println("Tablero número: " + Byte.toString(z));
                for(byte y = 0; y < this.LONGITUD; y++)
                {
                    for(byte x = 0; x < this.LONGITUD; x++)
                    {
                        System.out.printf("%-3s", this.tablero[x][y][z]);
                    }
                    System.out.println("");
                }
            }
        }
    }
}