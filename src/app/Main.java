package app;

import java.util.List;
import java.util.Scanner;

import constants.Color;
import constants.Const;
import dto.WikiDto;
import model.WikiReader;

/**
 * ファイルベースWikiアプリ
 * 
 * Wikiアプリ開発07 - wikiの全文検索を実装しよう！
 *
 * Mainクラス
 * ファイルベースWikiアプリの実行エントリポイントを持つクラス
 * @author　Say Consulting Group
 * @version　1.0.0
 */
public class Main {

  /**
   * mainメソッド
   * ファイルベースwikiアプリのエントリポイント
   * @param args コマンドライン引数(未使用)
   */
  public static void main(String[] args) {

    // ウェルカムメッセージ表示
    System.out.println(Const.WELCOME_MESSAGE);

    // wikiのリスト読み込み
    List<WikiDto> list = WikiReader.getAll();

    // 各値初期化
    String inpStr = "";         //入力されたのが文字の場合のために作成。
    int inpVal = 0;
    int listSize = list.size();
    boolean next = true;

    // 標準入力のスキャナーを生成
    try (Scanner sc = new Scanner(System.in)) {
      // キーボードから0が入力されるまでループする
      do {
        // wikiのリスト表示
        showIndex(list);
        System.out.print(Const.IMPUT_MESSAGE);
        // キーボードからの入力を受け取り変数に格納
        inpStr = sc.nextLine();

        try {
          inpVal = Integer.parseInt(inpStr);

          if (inpVal > listSize || inpVal < 0) {
            System.out.println(String.format("0～%dまでの数値を入力してください。", listSize));
          } else if (inpVal == 0) {
            // 終了する
            next = false;
          } else {
            // 指定番号のwikiを表示
            WikiDto d = list.get((int) inpVal - 1);   //特にいみがない。(キャストダウンの)
            System.out.println(d.toString());
          }
        } catch (NumberFormatException e) {
          // 入力が数字ではなかった場合、全文検索を行う
          searchWiki(list, inpStr);
        }

      } while (next);
    }

    // 終了メッセージを出力
    System.out.println(Const.FAIRWELL_MESSAGE);
  }

  /**
   * showIndexメソッド
   * wikiのリストを表示する
   * 繰り返し実行される一覧表示処理を外出しし、mainメソッドのコードを読みやすくする
   * 一行に表示するwikiの数はConstクラスの定数で変更可能
   * @param list wikiDtoのリスト
   */
  private static void showIndex(List<WikiDto> list) {

    int i = 0;          //int i=0 ~ i++　で改行の設定をきめらるる。１つずつ取り出すfor拡張文。i:wikiっをいくつ表示したかを数えてここで改行する。4つ表示する時に改行を入れiを使う。
    for (WikiDto item : list) {   //一行にいくつ表示するかを調べる。4つ目で改行するという目的のためにiを使ってる。
      System.out.print(item.index());

      // 一定数の表示後は改行し、改行しない場合は余白を表示
      String margin = i % Const.INDEX_LENGTH == Const.INDEX_LENGTH - 1 ? "\n\n" : "  ";
      System.out.print(margin);
      i++;
    }
  }
  
  /**
   * searchWikiメソッド
   * 指定文字列をタイトルか本文に含むwikiを表示する
   * @param list wikiDtoのリスト
   * @param q 検索文字列
   */
  private static void searchWiki(List<WikiDto> list, String q) {   //for文の中で１つずつwiki調べる・取り出す。一つ見つかったらiが増えていく。
    int i = 0;
    for (WikiDto item : list) {
      if (item.isMatched(q)) {
        System.out.println(item);
        i++;
      }
    }
    
    // 一件もヒットしなかった場合はその旨表示する
    if (0 == i) {  //ループを抜けてもiが0だったら一つもwikiが一つも見つからないことを示す。
      System.out.println(Color.Red.coloredStr("一致するwikiが見つかりませんでした。\n"));
    }
  }
}
