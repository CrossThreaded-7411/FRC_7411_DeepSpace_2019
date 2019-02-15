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
      cargoLevel1(7756),
      cargoLevel2(14496),
      cargoLevel3(21040),
      
      hatchLevel1(4032),
      hatchLevel2(11307),
      hatchLevel3(18147);

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