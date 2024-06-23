package com.bliss.blissapp.Controller;
import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Model.Quote;
import com.bliss.blissapp.Service.NextSequenceService;
import com.bliss.blissapp.Service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
@CrossOrigin
public class QuoteController {
    private final QuoteService quoteService;
    private final NextSequenceService nextSequenceService;

    @GetMapping("/all")
    public List<Quote> getAllQuotes(){
        return quoteService.getAllQuotes();
    }

    @PostMapping("/")
    public void createQuote(@RequestBody Quote quote) {
        quote.setId(nextSequenceService.getNextSequence("customSequences"));
        quoteService.createQuote(quote);
    }

    @GetMapping("/random")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }
}
