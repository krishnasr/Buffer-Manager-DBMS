
package bufmgr;

import diskmgr.*;
import global.*;

  /**
   * class Policy is a subclass of class Replacer use the given replacement
   * policy algorithm for page replacement
   */
class Clock extends  Replacer {   
//replace Policy above with impemented policy name (e.g., Lru, Clock)

  //
  // Frame State Constants
  //
  protected static final int AVAILABLE = 10;
  protected static final int REFERENCED = 11;
  protected static final int PINNED = 12;

  //Following are the fields required for LRU and MRU policies:
  /**
   * private field
   * An array to hold number of frames in the buffer pool
   */

    private int  frames[];
 
  /**
   * private field
   * number of frames used
   */   
  private int  nframes;

  /** Clock head; required for the default clock algorithm. */
  protected int head;

  /**
   * This pushes the given frame to the end of the list.
   * @param frameNo	the frame number
   */
  private void update(int frameNo)
  {
     //This function is to be used for LRU and MRU
  }

  /**
   * Class constructor
   * Initializing frames[] pinter = null.
   */
    public Clock(BufMgr mgrArg)
    {
      super(mgrArg);
      // initialize the frame states
    for (int i = 0; i < frametab.length; i++) {
      frametab[i].state = AVAILABLE;
    }
      // initialize parameters for LRU and MRU
      nframes = 0;
      frames = new int[frametab.length];

    // initialize the clock head for Clock policy
    head = 0;
    }
  /**
   * Notifies the replacer of a new page.
   */
  public void newPage(FrameDesc fdesc) {
    // no need to update frame state
  }

  /**
   * Notifies the replacer of a free page.
   */
  public void freePage(FrameDesc fdesc) {
    fdesc.state = AVAILABLE;
  }

  /**
   * Notifies the replacer of a pined page.
   */
  public void pinPage(FrameDesc fdesc) {
        
  }

  /**
   * Notifies the replacer of an unpinned page.
   */
  public void unpinPage(FrameDesc fdesc) {

  }
  
  /**
   * Finding a free frame in the buffer pool
   * or choosing a page to replace using your policy
   *
   * @return 	return the frame number
   *		return -1 if failed
   */

 public int[] pickVictim()
 {
    int times=1;
   int victim=-1;
   int secondchance=0;
    while(head<frametab.length && times<3){
      if(frametab[head].state==PINNED){
        head=(head+1)%frametab.length;
        if(head==0){
          times=times+1;
        }
      }
      if(frametab[head].state==REFERENCED){
        frametab[head].state=AVAILABLE;
        secondchance=secondchance+1;
        head=(head+1)%frametab.length;
        if(head==0){
          times=times+1;
        }
      }
      if(frametab[head].state==AVAILABLE)
      {
        victim=head;
        head=(head+1)%frametab.length;
        if(head==0){
          times=times+1;
        }
        break;
      }
    }
    int [] arr1=new int[2];
    arr1[0]=victim;
    arr1[1]=secondchance;
   //System.out.print("CLOCK Replacement Policy => Second Chance Bit was flipped for following number of pages before reaching victim => "+ noOfPagesUsingSecondChance);
   //System.out.println("CLOCK Replacement Policy => Returning Head value as => "+ head);
   return arr1;
 }
 }

