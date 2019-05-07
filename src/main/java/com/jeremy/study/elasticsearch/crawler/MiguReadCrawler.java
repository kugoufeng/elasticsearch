package com.jeremy.study.elasticsearch.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.jeremy.study.elasticsearch.domain.Book;
import org.jsoup.nodes.Element;

public class MiguReadCrawler extends BreadthCrawler {

    public MiguReadCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /**设置爬取的网站地址
         * addSeed 表示添加种子
         * 种子链接会在爬虫启动之前加入到抓取信息中并标记为未抓取状态.这个过程称为注入*/
        this.addSeed("http://wap.cmread.com/r/p/index.jsp");

        /** addRegex 参数为一个 url 正则表达式, 可以用于过滤不必抓取的链接，如 .js .jpg .css ... 等
         * 也可以指定抓取某些规则的链接，如下 addRegex 中会抓取 此类地址：
         * */
        this.addRegex("http://wap.cmread.com/r/.*");

        /**设置线程数*/
        setThreads(5);
        /**设置爬取的url最大值*/
        getConf().setTopN(1000);

        /**
         * 是否进行断电爬取，默认为 false
         * setResumable(true);
         */
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();

        /**如果此页面地址 确实是要求爬取网址，则进行取值
         */
        if (page.matchUrl("http://wap.cmread.com/r/[1-9][0-9]{8}/index.htm.*")) {
            /**
             * 通过 选择器 获取图书名称、封面、作者、简介、图书阅读地址
             * */
            Element bookName = page.select(".bookName").first();
            if (null != bookName) {
                Book book = new Book();
                book.setBookName(bookName.text());
                Element bookImage = page.select(".bookImg").first();
                Element child = bookImage.child(0);
                String src = child.attr("src");
                book.setBookCover(src);
                Element author = page.select(".cmr-bkauthor-detail").first();
                book.setBookAuthor(author.child(0) == null ? author.text() : author.child(0).text());
                Element shortDesc = page.select(".shortDesc").first();
                book.setShortDesc(shortDesc.text());
                Element readUrl = page.select(".free").first();
                book.setReadUrl("http://wap.cmread.com/r".concat(readUrl.attr("href")));
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /**
         * DemoAutoNewsCrawler 构造器中会进行 数据初始化，这两个参数接着会传给父类
         * super(crawlPath, autoParse);
         * crawlPath：表示设置保存爬取记录的文件夹，本例运行之后会在应用根目录下生成一个 "crawl" 目录存放爬取信息
         * */
        MiguReadCrawler crawler = new MiguReadCrawler("crawl", true);
        /**
         * 启动爬虫，爬取的深度为4层
         * 添加的第一层种子链接,为第1层
         */
        crawler.start(4);
    }

}
