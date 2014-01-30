package classes;


public class MissionControlUnit {
	
	public static void main(String[] args){
		Rover tiffysRover = null;
		String landingPosition = "69S";
		String movement = "MMMMMMMMMMMMMMMM";
		if (args.length > 1){
			landingPosition = args[0];
			movement = args[1];
		}
		try {
			tiffysRover = Rover.launch(landingPosition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (tiffysRover != null){
			try{
				String resultedPosition = tiffysRover.move(movement);
				System.out.println(resultedPosition);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}else
			System.out.println("mission aborted!");
			
	}

}
