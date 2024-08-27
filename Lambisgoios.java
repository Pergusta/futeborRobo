public class Lambisgoios implements Team{
	
	public String getTeamName(){
		return "Lambisgoios";
	}
	
	public void setTeamSide(TeamSide side){
		
	}
	
	public Robot buildRobot(GameSimulator s, int index){
		if(index == 0)
			return new Atacante(s);
		if(index == 1)
			return new Goleiro(s);

		return new Atacante(s);
	}
/////////////////////////////ATACANTE//////////////////////////////////////////
	class Atacante extends RobotBasic{
		Atacante(GameSimulator s){
			super(s);
		}
		
		float vloecidade = (float)Math.random() * 5 + 5;
		
		Sensor locator;

		public void setup(){
			System.out.println("Running!");
			locator = getSensor("BALL");
		}

		public void loop(){
			float angulo = locator.readValue(0);

			setRotation(angulo * vloecidade);
			setSpeed(10,-5);
			delay(100);
		}
	}
//////////////////////////////GOLEIRO/ZAGA//////////////////////////////////////
	class Goleiro extends RobotBasic{
		Goleiro(GameSimulator s){
			super(s);
		}

		  //float divisor = (float)Math.random() * 150 + 70;
      	float limitador = 1000;
		
		Sensor locator;
		Sensor[] ultrasonic_sensors = new Sensor[4];
		
		public void run(){
			locator = getSensor("BALL");

			ultrasonic_sensors[0] = getSensor("ULTRASONIC_FRONT");
			ultrasonic_sensors[1] = getSensor("ULTRASONIC_LEFT");
			ultrasonic_sensors[2] = getSensor("ULTRASONIC_BACK");
			ultrasonic_sensors[3] = getSensor("ULTRASONIC_RIGHT");
			
			System.out.println("Dibrando!");
			while(true){
				float angulo = locator.readValue(0);
				

				if(Math.abs(angulo) < 90)
				  setSpeed(10000f, angulo * limitador);
				else
					setSpeed(-1000f,0f);
				
				delay(100);
			}
		}
	}
	
}
