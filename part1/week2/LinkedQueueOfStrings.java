// Last in, first out

public class LinkedQueueOfStrings{
  // 最初と最後のノードを記録する
  private Node first, last;

  private class Node{
    String item;
    Node next;
  }

  public boolean isEmpty(){
    return first == null;
  }

  // last in
  public void enqueue(String item){
    // 以前のlastを記録しておく
    Node oldlast = last;
    // lastを初期化して，新しく追加されたitemを入れる
    last = new Node();
    last.item = item;
    last.next = null;
    if(isEmpty()) first = last; // emptyになった場合だけfirstにも影響がでる
    else          oldlast.next = last; // 以前のlastのnextを新しく追加したlastに指す
  }

  public String dequeue(){
    String item = first.item;
    // firstを一個進める
    first = first.next;
    if(isEmpty()) last = null;
    return item;
  }

// last in frist out なので, a b cの順でdequeueされる!!!
  public static void main(String[] args) {
      LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
      System.out.print(queue.isEmpty() + "\n");
      queue.enqueue("a");
      queue.enqueue("b");
      queue.enqueue("c");
      System.out.print(queue.isEmpty() + "\n");
      System.out.print(queue.dequeue() + "\n");
      System.out.print(queue.dequeue() + "\n");
      System.out.print(queue.dequeue() + "\n");
      System.out.print(queue.isEmpty() + "\n");
  }

}
