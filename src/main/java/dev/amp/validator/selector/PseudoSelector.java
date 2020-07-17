/*
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  ====================================================================
 */


/*
 * Changes to the original project are Copyright 2019, Verizon Media Inc..
 */

package dev.amp.validator.selector;

import dev.amp.validator.css.Token;
import dev.amp.validator.css.TokenType;
import dev.amp.validator.visitor.SelectorVisitor;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

/**
 * A pseudo selector can match either pseudo classes or pseudo elements.
 * http://www.w3.org/TR/css3-selectors/#pseudo-classes
 * http://www.w3.org/TR/css3-selectors/#pseudo-elements.
 *
 * Typically written as ':visited', ':lang(fr)', and '::first-line'.
 *
 * isClass: Pseudo selectors with a single colon (e.g., ':visited')
 * are pseudo class selectors. Selectors with two colons (e.g.,
 * '::first-line') are pseudo elements.
 *
 * func: If it's a function style pseudo selector, like lang(fr), then func
 * the function tokens. TODO(powdercloud): parse this in more detail.
 *
 * @author nhant01
 * @author GeorgeLuo
 */

public class PseudoSelector extends Selector {

    /**
     * @param {boolean} isClass
     * @param {string} name
     * @param {!Array<!tokenize_css.Token>} func
     */
    public PseudoSelector(boolean isClass, @Nonnull String name, @Nonnull final List<Token> func) {
        super();
        /** @type {boolean} */
        this.isClass = isClass;
        /** @type {string} */
        this.name = name;
        /** @type {!Array<!tokenize_css.Token>} */
        this.func = func;
    }

    @Override
    public void forEachChild(Consumer<Selector> selector) {}

    /**
     * visits a selector
     * @param visitor a SelectorVisitor instance
     */
    public void accept(@Nonnull final SelectorVisitor visitor) {
        visitor.visitPseudoSelector(this);
    }

    /**
     * get token type
     * @return the token type
     */
    @Override
    public TokenType getTokenType() {
        return TokenType.PSEUDO_SELECTOR;
    }

    /**
     *
     */
    private boolean isClass;

    /**
     *
     */
    private final String name;

    /**
     *
     */
    private final List<Token> func;
}