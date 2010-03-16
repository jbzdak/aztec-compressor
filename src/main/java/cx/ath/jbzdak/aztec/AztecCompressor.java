package cx.ath.jbzdak.aztec;

import cx.ath.jbzdak.aztec.states.AztecState;

/**
 * @author Jacek Bzdak jbzdak@gmail.com
 *         Date: Mar 9, 2010
 */
public class AztecCompressor {

   final DataSource dataSource;

   AztecState state;

   public AztecCompressor(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public void onNewPoint(Point point){
      state.addPoint(point);
      if(state.getNextState()!=null){
         state.getCompressed();
         //do something
         state = state.getNextState(); 
      }
   }
}
