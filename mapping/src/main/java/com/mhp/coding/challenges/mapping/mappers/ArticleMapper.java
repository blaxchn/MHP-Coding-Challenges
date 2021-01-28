package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.blocks.*;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ArticleMapper {

    private final ModelMapper modelMapper;

    public ArticleMapper() {
        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        modelMapper.createTypeMap(GalleryBlock.class, ArticleBlockDto.class)
                .setConverter(mappingContext -> modelMapper.map(mappingContext.getSource(), GalleryBlockDto.class));

        modelMapper.createTypeMap(ImageBlock.class, ArticleBlockDto.class)
                .setConverter(mappingContext -> modelMapper.map(mappingContext.getSource(), ImageBlockDto.class));

        modelMapper.typeMap(ImageBlock.class, ImageBlockDto.class).addMappings(mapper -> {
           mapper.map(src -> src.getImage(), ImageBlockDto::setImage);
        });

        modelMapper.createTypeMap(TextBlock.class, ArticleBlockDto.class)
                .setConverter(mappingContext -> modelMapper.map(mappingContext.getSource(), TextBlockDto.class));

        modelMapper.createTypeMap(VideoBlock.class, ArticleBlockDto.class)
                .setConverter(mappingContext -> modelMapper.map(mappingContext.getSource(), VideoBlockDto.class));

        modelMapper.createTypeMap(Image.class, ImageDto.class)
                .setConverter(mappingContext -> modelMapper.map(mappingContext.getSource(), ImageDto.class));
    }

    /***
     * This method maps a single article to a Data Transfer Object,
     * that can be used as (part of a) JSON
     *
     * @param article A single instance of Article
     * @return A single instance of ArticleDto
     */
    public ArticleDto map(Article article){
        ArticleDto articleDto = modelMapper.map(article, ArticleDto.class);

        // TODO
        // Code below isn't working correctly, therefore it' commented.
        // The mapper doesn't map the field "Image" correctly.

        /*
        ArrayList<ImageDto> imageDtos = new ArrayList<>();
        for (ArticleBlock articleBlock : article.getBlocks()) {
           if (articleBlock instanceof ImageBlock) {
                ImageDto imageDto = modelMapper.map(((ImageBlock) articleBlock).getImage(), ImageDto.class);
                imageDtos.add(imageDto);
                System.out.println(imageDto.getId() + " " + imageDto.getUrl());
            }
        }
        */

        Collections.sort((List<ArticleBlockDto>) articleDto.getBlocks());
        return articleDto;
    }

    /***
     * This method maps a list of articles from entities
     * to a list that can be used as JSON
     *
     * @param articles A list of articles as entities
     * @return A list of articles as Data Transfer Object
     */
    public ArrayList<ArticleDto> map(List<Article> articles) {
        ArrayList<ArticleDto> articleDtos = new ArrayList<>();

        for (Article article : articles) {
            articleDtos.add(map(article));
        }

        return articleDtos;
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }
}
