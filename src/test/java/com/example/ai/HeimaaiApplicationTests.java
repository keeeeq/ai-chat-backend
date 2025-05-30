package com.example.ai;

import com.example.ai.config.CommonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Vector;

@SpringBootTest
class HeimaaiApplicationTests {

	@Autowired
	private OpenAiEmbeddingModel embeddingModel;

	@Autowired
	private VectorStore vectorStore;

	@Test
	public void testVectorStore(){
		Resource resource = new FileSystemResource("中二知识笔记.pdf");
		// 1.创建PDF的读取器
		PagePdfDocumentReader reader = new PagePdfDocumentReader(
				resource, // 文件源
				PdfDocumentReaderConfig.builder()
						.withPageExtractedTextFormatter(ExtractedTextFormatter.defaults())
						.withPagesPerDocument(1) // 每1页PDF作为一个Document
						.build()
		);
		// 2.读取PDF文档，拆分为Document
		List<Document> documents = reader.read();
		// 3.写入向量库
		vectorStore.add(documents);
		// 4.搜索
		SearchRequest request = SearchRequest.builder()
				.query("论语中教育的目的是什么")
				.topK(1)
				.similarityThreshold(0.6)
				.filterExpression("file_name == '中二知识笔记.pdf'")
				.build();
		List<Document> docs = vectorStore.similaritySearch(request);
		if (docs == null) {
			System.out.println("没有搜索到任何内容");
			return;
		}
		for (Document doc : docs) {
			System.out.println(doc.getId());
			System.out.println(doc.getScore());
			System.out.println(doc.getText());
		}
	}

	@Test
	void contextLoads() {
	}

}
