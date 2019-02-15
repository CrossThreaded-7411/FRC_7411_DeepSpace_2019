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
      cargoLevel1(3000),
      cargoLevel2(6000),
      cargoLevel3(9000),
      
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

   public enum Misc
   {
      home(0), load(500);

      private int value;

      private Misc(int value) 
      {
         this.value = value;
      }

      public int getVal() {
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