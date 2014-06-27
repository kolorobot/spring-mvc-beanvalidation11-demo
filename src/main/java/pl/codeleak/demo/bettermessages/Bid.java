package pl.codeleak.demo.bettermessages;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class Bid {

    @Size.List({
            @Size(min = 5, message = "{bid.bidder.min.message}"),
            @Size(max = 10, message = "{bid.bidder.max.message}")
    })
    private String bidder;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "{bid.expiresAt.message}")
    private Date expiresAt;

    @NotNull
    @DecimalMin(value = "10.00", message = "{bid.price.message}")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    protected Bid() {
    }

    public Bid(String bidder, Date expiresAt, BigDecimal price) {
        this.bidder = bidder;
        this.expiresAt = expiresAt;
        this.price = price;
    }

    public String getBidder() {
        return bidder;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
