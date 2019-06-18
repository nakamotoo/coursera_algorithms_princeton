
// ボトムアップ型マージソート（再帰を使わない）

public class MergeBU{

  private static Comparable[] aux;

  private static boolean less(Comparable a, Comparable b){
    return a.compareTo(b) < 0;
  }

  private static boolean isSorted(Comparable[] a, int lo, int mid){
    for(int i = lo+1; i < mid; i++){
      if(less(a[i], a[i-1])) return false;
    }
    return true;
  }

  private static void merge(Comparable[] a, int lo, int mid, int hi){

    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);

    // copy a to aux
    for(int k = lo; k <= hi; k++){
      aux[k] = a[k];
    }

    // merge
    int i = lo, j = mid + 1;
    for(int k = lo; k <= hi; k++){
      if (i > mid) a[k] = aux[j++]; // 左が尽きた場合
      else if (j > hi) a[k] = aux[i++]; // 右が尽きた場合
      else if (less(aux[j], aux[i])) a[k] = aux[j++]; // 左の方が大きい場合
      else a[k] = aux[i++]; // 右の方が大きい場合
    }

    assert isSorted(a, lo, hi);
  }

  public static void sort(Comparable[] a){
    int N = a.length;
    aux = new Comparable[N];
    // sz → 左右のそれぞれの大きさ
    for(int sz = 1; sz < N; sz = sz+sz) // logN 回
      for(int lo = 0; lo < N - sz; lo += sz+sz)
        merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1)); // Math.min(lo+sz+sz-1, N-1) は配列の最後，ぴったりsz分ないかもしれないから
  }

  public static void main(String[] args) {
      Comparable[] a = {100, 40, 90, 98, 30, 20, 1};
      sort(a);
      for(int i = 0; i < 6; i++)
        System.out.print(a[i] + "\n");
  }

}
