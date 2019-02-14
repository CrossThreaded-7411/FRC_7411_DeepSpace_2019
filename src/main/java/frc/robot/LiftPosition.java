/**
 *  Class that organizes position values (encoder counts) used for preset lift position
 */
package frc.robot;

public class LiftPosition
{
   /**
    * Contains relative encoder count positions for rocket hatch and cargo
    */
   public enum Rocket
   {
      cargoLevel1(1000),
      cargoLevel2(2000),
      cargoLevel3(3000),
      
      hatchLevel1(1500),
      hatchLevel2(2500),
      hatchLevel3(3500);

      private int value;

      private Rocket(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }


   /**
    * Contains relative encoder count positions for cargo ship hatch and cargo
    */
   public enum CargoShip
   {
      cargoHeight(1000),
      hatchHeight(2500);

      private int value;

      private CargoShip(int value)
      {
         this.value = value;
      }

      public int getVal()
      {
         return value;
      }
   }
}